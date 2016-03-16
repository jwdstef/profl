package com.radixdigit.junitText;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


/**y
 * mybatis  orm 自动生成器
 * @author chengjun
 *
 */

public class CreateMapper {
	@Test
	public  void  text(){
		try {
			List<String> warnings = new ArrayList<String>();
			final boolean overwrite = true;
			File configFile = new File("src/com/radixdigit/junitText/generatorConfig.xml");
			System.out.println("路径为 : " + configFile.getAbsoluteFile());
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration configuration = cp.parseConfiguration(configFile);
			
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator mybatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
			mybatisGenerator.generate(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  
}
