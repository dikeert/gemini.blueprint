/******************************************************************************
 * Copyright (c) 2006, 2010 VMware Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at 
 * http://www.eclipse.org/legal/epl-v10.html and the Apache License v2.0
 * is available at http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses. 
 * 
 * Contributors:
 *   VMware Inc.
 *****************************************************************************/

package org.eclipse.gemini.blueprint.iandt.proxycreator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple test that touches proxies created through spring. While this test
 * doesn't do much by itself, it should always work even when the bundle it runs
 * in, is updated. Failure to do so implies incorrect usage of the appropriate
 * class loader.
 * 
 * Thanks to Alexei Akimov for the proof of concept.
 * 
 * @author Costin Leau
 * 
 */
public class Test {

	/** logger */
	private static final Log log = LogFactory.getLog(Test.class);

	private SomeInterface jdkProxy;
	private SomeInterface cglibProxy;


	/**
	 * Sets echo JDK proxy
	 * 
	 * @param echoJdkProxy
	 */
	public void setJdkProxy(SomeInterface echoJdkProxy) {
		this.jdkProxy = echoJdkProxy;
	}

	/**
	 * Sets echo CGLIB proxy
	 * 
	 * @param echoCglibProxy
	 */
	public void setCglibProxy(SomeInterface echoCglibProxy) {
		this.cglibProxy = echoCglibProxy;
	}

	public void test() {
		testInterface(jdkProxy);
		testInterface(cglibProxy);
	}

	// interact with the proxy to make sure the weaving process is successful
	private void testInterface(SomeInterface intfs) {
		try {
			log.info("testing proxy interception...");
			intfs.doSmth("hangar 18");
		}
		catch (Throwable th) {
			log.error("caught exception", th);
		}
	}
}
