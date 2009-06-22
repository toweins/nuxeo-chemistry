/*
 * (C) Copyright 2009 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Florent Guillaume
 */
package org.nuxeo.ecm.core.chemistry.jaxrs;

import org.apache.chemistry.atompub.server.jaxrs.AbderaResource;
import org.apache.chemistry.atompub.server.jaxrs.AbderaResponseProvider;
import org.nuxeo.ecm.core.chemistry.impl.NuxeoRepository;
import org.nuxeo.ecm.webengine.WebEngine;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.model.ComponentContext;
import org.nuxeo.runtime.model.DefaultComponent;

/**
 * Nuxeo Runtime Component whose activation is used to register things not
 * available through extension points: additional JAX-RS Providers, repository
 * for Chemistry.
 *
 * @author Florent Guillaume
 */
public class JaxrsNuxeoComponent extends DefaultComponent {

    @Override
    public void activate(ComponentContext context) throws Exception {
        // TODO should be done using an extension point
        WebEngine engine = Framework.getLocalService(WebEngine.class);
        engine.getRegistry().addMessageBodyWriter(new AbderaResponseProvider());

        // TODO should be done differently, not using a static field
        AbderaResource.repository = new NuxeoRepository("default");
    }

}
