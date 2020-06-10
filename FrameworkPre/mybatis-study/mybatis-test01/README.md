### 测试MyBatis中的handler
A. BaseTypeHandler  
1. 查询  
从jdbcType（varchar）到javaType（RelatedInfo）的转换
2. 新增或修改  
从javaType（RelatedInfo）到jdbcType（varchar）的转换  

B. ResultHandler  
处理mybatis返回的查询结果
