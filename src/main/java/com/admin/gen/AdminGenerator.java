package com.admin.gen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * ClassName:VrGenerator <br/>
 * Function: 代码生成. <br/>
 * Date: 2017年2月24日 下午2:03:56 <br/>
 *
 * @author weiming.chen
 * @version
 * @since JDK 1.7
 * @see
 */
public class AdminGenerator {

	/** 指定输出目录 */
	private static String outDir = "D://to//src";
	/** 指定注释创建者 */
	private static String author = "zlh";
	/** 指定数据库用户名 */
	private static String dbUserName = "root";
	/** 数据库密码 */
	private static String dbUserPwd = "";
	/** 数据库连接 */
	private static String dbUrl = "jdbc:mysql://localhost:3306/admin2?characterEncoding=utf8";
	/** 指定表名 */
	private static String[] inTable = new String[] { "qptabledb" };
	/** 指定实体模块包路径 */
	private static String entityPath = "entity.room";
	/** 指定service模块包路径 */
	private static String servicePath = "service.room";
	/** 指定service实现模块包路径 */
	private static String serviceImplPath = "service.room.impl";
	/** 指定dao模块包路径 */
	private static String mapperPath = "mapper";

	/**
	 * main,代码生成. <br/>
	 * Author: weiming.chen <br/>
	 * Create Date: 2017年3月7日 <br/>
	 * ===============================================================<br/>
	 * Modifier: weiming.chen <br/>
	 * Modify Date: 2017年3月7日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param args 参数
	 * @since JDK 1.7
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		GlobalConfig gc = new GlobalConfig();
		// 生成代码存放目录
		gc.setOutputDir(outDir);
		//作者名字(产生代码后，注释需要自己调整)
		gc.setAuthor(author);
		gc.setFileOverride(true);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(false);
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		mpg.setGlobalConfig(gc);

		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(dbUserName);
		dsc.setPassword(dbUserPwd);
		dsc.setUrl(dbUrl);
		mpg.setDataSource(dsc);

		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 需要生成的表
		strategy.setInclude(inTable);
		strategy.setSuperEntityClass("com.admin.entity.BaseModel");
		// strategy.setSuperEntityColumns(new String[] { "id", "creater_code", "create_time", "delflag", "operater_code", "operate_time" });
		mpg.setStrategy(strategy);
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.admin");
		pc.setEntity(entityPath);
		pc.setMapper("dao.room");
		pc.setService(servicePath);
		pc.setServiceImpl(serviceImplPath);
		pc.setXml(mapperPath);
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		mpg.execute();
	}
}
