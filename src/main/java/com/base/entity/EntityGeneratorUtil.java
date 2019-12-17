package com.base.entity;

import org.mybatis.generator.api.ShellRunner;

public class EntityGeneratorUtil {

	public static void main( String[] args ) {
        args = new String[] { "-configfile", "src\\main\\resources\\config\\generator.xml", "-overwrite" };
        ShellRunner.main(args);
    }
}
