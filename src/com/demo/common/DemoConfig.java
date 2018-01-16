package com.demo.common;

import com.demo.blog.BlogController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.Restful;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.jwttoken.AuthInterceptor;
import com.jfinal.plugin.jwttoken.JwtTokenPlugin;
import com.jfinal.template.Engine;
import com.wtms.common.model._MappingKit;
import com.wtms.controller.BroleController;
import com.wtms.controller.DepartmentController;
import com.wtms.controller.FaultController;
import com.wtms.controller.FlevelController;
import com.wtms.controller.FstateController;
import com.wtms.controller.HomeController;
import com.wtms.controller.KksController;
import com.wtms.controller.MenuController;
import com.wtms.controller.OperateTicketStateController;
import com.wtms.controller.PositionController;
import com.wtms.controller.RoleController;
import com.wtms.controller.UserController;
import com.wtms.controller.WorkTicketStateController;
import com.wtms.service.UserService;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("WebRoot", 8081, "/");
		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("WebRoot", 80, "/");
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
//		me.add("/", IndexController.class, "/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/blog", BlogController.class);			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/",HomeController.class);
		me.add("/menus",MenuController.class);
		me.add("/users",UserController.class);
		me.add("/positions",PositionController.class);
		me.add("/departments",DepartmentController.class);
		me.add("/broles",BroleController.class);
		me.add("/roles",RoleController.class);
		me.add("/kks",KksController.class);
		me.add("/fstates",FstateController.class);
		me.add("/flevels",FlevelController.class); 
		me.add("/faults",FaultController.class); 
		me.add("/workTicketStates",WorkTicketStateController.class);
		me.add("/operateTicketStates",OperateTicketStateController.class);
	}
	
	public void configEngine(Engine me) {
		me.addSharedFunction("/common/_layout.html");
		me.addSharedFunction("/common/_paginate.html");
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(true);
		arp.setDevMode(true);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
		
		//配置JwtToken插件
		me.add(new JwtTokenPlugin(new UserService()));
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new AuthInterceptor());
//		me.add(new Restful());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	public void afterJFinalStart(){
		UserService.addUserInfo();
	}
}
