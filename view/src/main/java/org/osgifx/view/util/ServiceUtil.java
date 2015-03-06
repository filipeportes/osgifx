package org.osgifx.view.util;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgifx.view.Activator;

/**
 *
 * @author filipeportes
 */
public class ServiceUtil {

    public static Object getService(String name) {
        BundleContext bundleContext = FrameworkUtil.getBundle(Activator.class).getBundleContext();

        ServiceReference sr = bundleContext.getServiceReference(name);
        return bundleContext.getService(sr);
    }
}
