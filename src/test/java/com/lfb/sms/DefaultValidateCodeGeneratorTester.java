package com.lfb.sms;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.lfb.configure.BeansManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BeansManager.class, loader=AnnotationConfigContextLoader.class)
public class DefaultValidateCodeGeneratorTester {

	
	@Autowired
	private ValidateCodeGenerator generator;
	
	@Autowired
	DefaultSmsContainer smsContainer;
	
	@Ignore
	@Test
	public void testGen(){
		System.out.println(generator.gen(null));
	}
	
	@Test
	public void testSmsContainer(){
		String code = smsContainer.genValidateCode();
		smsContainer.save("1234", code);
		assertTrue(smsContainer.check("1234", code));
		assertFalse(smsContainer.check("1234", "1231"));
		assertFalse(smsContainer.check("123", code));
	}
	
}
