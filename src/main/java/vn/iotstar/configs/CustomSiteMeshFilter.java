//package vn.iotstar.configs;
//
//import org.sitemesh.builder.SiteMeshFilterBuilder;
//import org.sitemesh.config.ConfigurableSiteMeshFilter;
//
//public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
//
//	@Override
//
//	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
//
//		// Assigning default decorator if no path specific decorator found
//		builder.addDecoratorPath("/*", "web.jsp")
//				// Map decorators to specific path patterns.
//				.addDecoratorPath("/admin", "admin.jsp").addDecoratorPath("/admin/**", "admin.jsp")
//				.addDecoratorPath("/manager/**", "manager.jsp").addDecoratorPath("/user/**", "user.jsp")
//
//				// Exclude few paths from decoration.
//				.addExcludedPath("/alogin*").addExcludedPath("/alogin/*").addExcludedPath("/api/**")
//				.addExcludedPath("/api/**").addExcludedPath("/assets/**").addExcludedPath("/demo_files/**")
//				.addExcludedPath("/static/**");
//
//	}
//
//}
