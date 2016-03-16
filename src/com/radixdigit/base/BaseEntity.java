package com.radixdigit.base;

import java.io.Serializable;
/**
 * 实体类基类，由于Mybatis映射实体类不会自动实现序列化，并且主要为了在泛型中约束。建此基类
 * 也可對实体类部分属性统一维护
 * @author chengjun
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8450240432057691206L;


}
