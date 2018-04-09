package com.TiHom.studentmanager.utils;

import com.TiHom.studentmanager.exception.DaoException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2018/4/8 13:12
 * 手写的对dao层一些操作的封装和处理
 * @author TiHom
 */
public class DaoUtil {
    /**
     * 执行sql语句，使用预编译防止注入
     * @param sql sql语句
     * @param params SQL占位符(?)的参数
     */
    public void update(String sql,Object...params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //得到连接
            connection = JdbcUtil.getConnection();
            //预编译
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<params.length;i++){
                //读取第i个参数，赋值到占位符
                preparedStatement.setObject(i+1,params[i]);
            }
            //查询
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //释放资源
            JdbcUtil.realeaseConnection(preparedStatement,connection);
        }
    }

    /**
     * 基本的增删改，不包括查询
     * @param sql
     */
    public void update(String sql){
        Connection connection = null;
        Statement statement = null;
        try{
            //得到连接
            connection = JdbcUtil.getConnection();
            statement = connection.createStatement();
            //发送sql语句，调用statement对象的executeQuery方法向数据库发送sql语句
            statement.execute(sql);
        }catch (Exception e){
            throw new DaoException(e.getMessage(),e);
        } finally {
            //释放资源
            JdbcUtil.realeaseConnection(statement,connection);
        }
    }

    /**
     *
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> T get(Class<T> clazz, String sql, Object... params){
        List<T> result = getToList(clazz,sql,params);
        if(result.size()>0){
            return result.get(0);
        }
        return null;
    }

    /**
     * 查询多条记录，返回对应的对象集合
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> getToList(Class<T> clazz,String sql,Object... params){
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<params.length;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
            //获取结果集
            resultSet = preparedStatement.executeQuery();
            //这里得到结果集后，得到Map的List，其中一个Map对象就是一条记录，Map的key为resultSet中列的别名，Map的value为列的值
            List<Map<String,Object>> values = handleResultSetToMapList(resultSet);
            //将mapList转换成bean对象集合
            list = transferMapListToBeanList(clazz,values);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(),e);
        } finally {
            JdbcUtil.releaseConnection(resultSet,preparedStatement,connection);
        }
        return list;
    }

    /**
     * 处理结果集转换成MapList
     * @param resultSet
     * @return 返回MapList
     * @throws SQLException
     */
    public List<Map<String,Object>> handleResultSetToMapList(ResultSet resultSet) throws SQLException {
        List<Map<String,Object>> values = new ArrayList<>();
        //获取列标签的集合
        List<String> columnLabels = getColumnLabels(resultSet);
        //创建一个对应列名和属性的map对象
        Map<String,Object> map = null;
        while (resultSet.next()){
            map = new HashMap<>();
            for(int i=0;i<columnLabels.size();i++){
                //获取列标签
                String columnLabel = columnLabels.get(i);
                //根据列名获取，根据索引获取都可以
                Object value = resultSet.getObject(columnLabel);
                //将列名和列属性组成一个map对象
                map.put(columnLabel,value);
            }
            //添加一个listmap对象
            values.add(map);
        }
        return values;
    }

    /**
     * 把Map的List转为clazz对应的List集合，其中Map的key即为clazz对应的对象的propertyName，
     * 而Map的value即为clazz对应的对象的propertyValue
     * @param clazz
     * @param values
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private <T> List<T> transferMapListToBeanList(Class<T> clazz,List<Map<String,Object>> values)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //创建要转换对象的List
        List<T> result = new ArrayList<>();
        T bean = null;
        if(values.size()>0){
            for(Map<String,Object> map:values){
                //定义一个判断条件
                int mapsize = 0;
                for(Map.Entry<String,Object> entry:map.entrySet()){
                    //一开始我debug后发现有问题进行的第一次修改
//                    if(bean==null&&values.size()==1){
//                        //当只返回一个对象时，只能创建一次对象
//                        bean = clazz.newInstance();
//                    }
                    String propertyName = entry.getKey();
                    Object value = entry.getValue();
                    //优化后的做法
                    if(mapsize==0){
                        //当返回多个对象时，多个entry要对应一个bean，所以要在进入一个map时只能创建一次bean
                        bean = clazz.newInstance();
                        //改变判断条件
                        mapsize = map.size();
                    }

                    //将bean对象的各条属性赋予值
                    BeanUtils.setProperty(bean,propertyName,value);
                }
                //把object对象放入到list中，遍历Map对象，用反射填充对象的属性值：属性名为Map中的key，属性值为Map中的value
                result.add(bean);
            }
        }
        return result;
    }

    /**
     * 获取结果集的ColumnLabel对应的List
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private List<String> getColumnLabels(ResultSet resultSet) throws SQLException {
        //获取列标签集合
        List<String> labels = new ArrayList<>();
        //获取结果集元数据——就是数据库中表中的属性
        ResultSetMetaData rsmd = resultSet.getMetaData();
        for(int i=0;i<rsmd.getColumnCount();i++){
            //这个是获取列别名,getColumnName是显示列名
            labels.add(rsmd.getColumnLabel(i+1));
        }
        //返回标签集
        return labels;
    }
}
