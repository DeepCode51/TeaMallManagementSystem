package com.shop.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.Utils.PageBean;
import com.shop.Utils.UUIDUtiils;
import com.shop.mapper.GonggaoMapper;
import com.shop.po.Adminuser;
import com.shop.po.Category;
import com.shop.po.Categorysecond;
import com.shop.po.Gonggao;
import com.shop.po.GonggaoExample;
import com.shop.po.Message;
import com.shop.po.Orders;
import com.shop.po.Product;
import com.shop.po.User;
import com.shop.po.GonggaoExample.Criteria;
import com.shop.service.CategorySecondService;
import com.shop.service.CategoryService;
import com.shop.service.MessageService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.service.UserService;


@Controller
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	public CategoryService categoryService;
	@Autowired
	public CategorySecondService categorySecondService;
	@Autowired
	public ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private GonggaoMapper gonggaoMapper;
	@RequestMapping("/admin/admin_findAll")
	public String admin_findAll(Model model,HttpServletRequest request) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		List<User> userList = userService.admin_findAll();
		model.addAttribute("userList", userList);
		return "admin/user/list";
	}

	@RequestMapping("/admin/adminCategory_findAll")
	public String adminCategory_findAll(Model model, HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		List<Category> categoryList = categoryService.adminbFindCategory();
		/*for (Category category : categoryList) {
			System.out.println(category.getCname());
		}*/
		model.addAttribute("categoryList", categoryList);
		return "admin/category/list";
	}

	@RequestMapping("/admin/adminCategory_add")
	public String adminCategory_add(Model model,HttpServletRequest request) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
//		List<Category> categoryList = categoryService.adminbFindCategory();
//		model.addAttribute("categoryList", categoryList);
		return "admin/category/add";
	}

	@RequestMapping("/admin/adminCategory_save")
	public String adminCategory_save(@RequestParam String cname,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Category addCategory = new Category();
		addCategory.setCname(cname);
		categoryService.addCategory(addCategory);
		return "redirect:/admin/adminCategory_findAll.action";
	}

	@RequestMapping("/admin/adminCategory_edit")
	public String adminCategory_delete(@RequestParam int cid, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Category existCategory = categoryService.findCategory(cid);
		model.addAttribute("existCategory", existCategory);
		return "admin/category/edit";
	}

	@RequestMapping("/admin/adminCategory_update")
	public String adminCategory_update(@RequestParam int cid,
			@RequestParam String cname,HttpServletRequest request) {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		categoryService.adminCategory_update(category);
		return "redirect:/admin/adminCategory_findAll.action";
	}

	@RequestMapping("/admin/adminCategory_delete")
	public String adminCategory_delete(@RequestParam int cid,HttpServletRequest request) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		categorySecondService.adminCategorySecond_deleteByCid(cid);
		categoryService.deleteCategoryByCid(cid);
		return "redirect:/admin/adminCategory_findAll.action";
	}

	// admin 的二级分类的管理
	@RequestMapping("/admin/adminCategorySecond_findAllByPage")
	public String adminCategorySecond_findAllByPage(@RequestParam int page,Model model,HttpServletRequest request) {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		PageBean<Categorysecond> csPageBean = categorySecondService
				.adminCategorySecond_findAllByPage(page);
		/*List<Categorysecond> list = csPageBean.getList();
		for (Categorysecond categorysecond : list) {
			System.out.println(categorysecond.getCsname());
		}*/
		model.addAttribute("csPageBean", csPageBean);
		return "admin/categorysecond/list";
	}
	@RequestMapping("admin/adminCategorySecond_addPage")
	public String adminCategorySecond_addPage(Model model,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		List<Category> categoryList = categoryService.adminbFindCategory();
		model.addAttribute("categoryList", categoryList);
		return "admin/categorysecond/add";
	}
	@RequestMapping("admin/adminCategorySecond_save")
	public String adminCategorySecond_save(@RequestParam String csname,@RequestParam int cid,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Categorysecond categorysecond = new Categorysecond();
		categorysecond.setCsname(csname);
		categorysecond.setCid(cid);
		categorySecondService.adminCategorySecond_save(categorysecond);
		return "redirect:/admin/adminCategorySecond_findAllByPage.action?page=1";
	}
	@RequestMapping("admin/adminCategorySecond_edit")
	public String adminCategorySecond_edit(@RequestParam int csid,Model model,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Categorysecond findByCsid = categorySecondService.findByCsid(csid);
		model.addAttribute("findByCsid", findByCsid);
		return "admin/categorysecond/edit";
	}
	@RequestMapping("admin/adminCategorySecond_update")
	public String adminCategorySecond_update(@RequestParam String csname,@RequestParam int csid,HttpServletRequest request){
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Categorysecond categorysecond = new Categorysecond();
		categorysecond.setCsname(csname);
		categorysecond.setCsid(csid);
		categorySecondService.adminCategorySecond_update(categorysecond);
		return "redirect:/admin/adminCategorySecond_findAllByPage.action?page=1";
	}
	@RequestMapping("admin/adminCategorySecond_delete")
	public String adminCategorySecond_delete(@RequestParam int csid,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		categorySecondService.adminCategorySecond_delete(csid);
		return "redirect:/admin/adminCategorySecond_findAllByPage.action?page=1";
	}
	//admin的茶叶管理
	@RequestMapping("/admin/adminProduct_findAllByPage")
	public String adminProduct_findAllByPage(@RequestParam int page, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		PageBean<Product> allProPageBean = productService.findAllProduct(page);
		model.addAttribute("allProPageBean", allProPageBean);
		return "admin/product/list";
	}

	@RequestMapping("/admin/adminProduct_addPage")
	public String adminProduct_addPage(Model model,HttpServletRequest request) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		//查询出所有的二级分类
		List<Categorysecond> cslist = categorySecondService.findAll();
		/*for (Categorysecond categorysecond : cslist) {
			System.out.println(categorysecond.getCsname());
		}*/
		model.addAttribute("cslist", cslist);
		return "admin/product/add";
	}

	@RequestMapping("/admin/adminProduct_save")
	public String adminProduct_save(Product product,HttpServletRequest request,MultipartFile file) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		product.setPdate(new Date());
//		上传图片
		if (file != null) {
			String path = request.getServletContext().getRealPath(
					"/products");
			String uploadFileName = file.getOriginalFilename();
			String fileName = UUIDUtiils.getUUID()+uploadFileName;
			File diskFile = new File(path + "//" + fileName);
			file.transferTo(diskFile);
			product.setImage("products/" + fileName);
		}
		System.out.println(product.getImage());
		productService.adminProduct_save(product);
		return "redirect:/admin/adminProduct_findAllByPage.action?page=1";
	}
	
	@RequestMapping("admin/adminProduct_deletecs")
	public String adminProduct_deletecs(@RequestParam int pid,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		productService.adminProduct_deletecs(pid);
		return "redirect:/admin/adminProduct_findAllByPage.action?page=1";
	}
	/**
	 * 编辑茶叶
	 * 
	 * @param pid
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("admin/adminProduct_edit")
	public String adminProduct_edit(@RequestParam int pid,Model model,HttpServletRequest request) throws Exception{
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		Product product = productService.finbProductByPid(pid);
		model.addAttribute("findByPid", product);
		//查询出所有的二级分类
		List<Categorysecond> cslist = categorySecondService.findAll();
		model.addAttribute("cslist", cslist);
		return "admin/product/edit";
	}
	
	@RequestMapping("/admin/adminProduct_update")
	public String adminProduct_update(Product product,HttpServletRequest request,MultipartFile file) throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		product.setPdate(new Date());
//		上传图片
		if (!file.isEmpty()) {
 			String path = request.getServletContext().getRealPath(
					"/products");
			String uploadFileName = file.getOriginalFilename();
			String fileName = UUIDUtiils.getUUID()+uploadFileName;
			File diskFile = new File(path + "//" + fileName+".jpg");
			file.transferTo(diskFile);
			product.setImage("products/" + fileName+".jpg");
		}
		 
		productService.adminProduct_update(product);
		return "redirect:/admin/adminProduct_findAllByPage.action?page=1";
	}

	/** 订单管理模块  **/
	
	@RequestMapping("/admin/adminOrder_findAllByPage")
	public String adminOrder_findAllByPage(@RequestParam int page, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		PageBean<Orders> allProPageBean = orderService.findAllOrderByStateAndPage(page);
		model.addAttribute("pageBean", allProPageBean);
		return "admin/order/list";
	}
	
	//adminOrder_findByState
	@RequestMapping("/admin/adminOrder_findByState")
	public String adminOrder_findByState(@RequestParam int page,@RequestParam int state, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		PageBean<Orders> allProPageBean = orderService.findAllOrderByStateAndPage(state,page);
		model.addAttribute("pageBean", allProPageBean);
		return "admin/order/list";
	}
	
	//订单详情
	//adminOrder_findOrderItem
	@RequestMapping("/admin/adminOrder_findOrderItem")
	public String adminOrder_findOrderItem(@RequestParam int oid, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		Orders orderItems = orderService.findOrderByOid(oid);
		model.addAttribute("orderItems", orderItems);
		return "admin/order/list";
	}
	
	//更新订单状态
	@RequestMapping("/admin/adminOrder_updateState")
	public String adminOrder_updateState(@RequestParam int oid, @RequestParam int status, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		orderService.updateOrderStatus(oid, status);
		
		return "redirect:/admin/adminOrder_findAllByPage.action?page=1";
	}
	
	//后台留言管理 
	@RequestMapping("/admin/adminMessage_findAllByPage")
	public String adminMessage_findAllByPage(@RequestParam int page, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		PageBean<Message> pageBean = messageService.findAllMessageByPage(page);
		model.addAttribute("pageBean", pageBean);
		
		return "admin/message/list";
	}
	
	@RequestMapping("/admin/adminMessage_delete")
	public String adminOrder_updateState(@RequestParam int messageid, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		
		messageService.deleteMessage(messageid);
		
		return "redirect:/admin/adminMessage_findAllByPage.action?page=1";
	}
	
	
	// 显示留言板全部留言
	@RequestMapping("/admin/adminGonggao_findAllByPage")
	public String adminGonggao_findAllByPage(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {
		PageBean<Gonggao> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置4个
		int limitPage =4;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		;
		totlePage = (int) gonggaoMapper.countByExample(new GonggaoExample());
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		GonggaoExample example = new GonggaoExample();
		example.setLimit(limitPage);
		if(beginPage<0)beginPage=0;
		example.setOffset(beginPage);
		//茶叶集合
		List<Gonggao> list = gonggaoMapper.selectByExample(example) ;
		pageBean.setList(list);
			model.addAttribute("pageBean", pageBean);
		return "/admin/gonggao/list";
	} 
	
	@RequestMapping("/admin/adminGonggao_delete")
	public String adminGonggao_delete(@RequestParam int id, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		GonggaoExample example = new GonggaoExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		gonggaoMapper.deleteByExample(example);
 		
		return "redirect:/admin/adminGonggao_findAllByPage.action?page=1";
	}
	//后台留言管理 
	@RequestMapping("/admin/adminGonggao_add")
	public String gonggaoAdd(Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		return "admin/gonggao/add";
	}

	@RequestMapping("/admin/saveGonggao")
	public String saveMessage(@RequestParam String title,@RequestParam String content,HttpServletRequest request,Model model) throws Exception {
		Gonggao gonggao = new Gonggao();
		gonggao.setContent(content);
 		gonggao.setTime(new Date());
 		gonggao.setTitle(title);
		gonggaoMapper.insertSelective(gonggao);
		return "redirect:/admin/adminGonggao_findAllByPage.action?page=1";
	}
	
	@RequestMapping("/admin/adminGonggao_edit")
	public String adminGonggao_edit(@RequestParam int id, Model model,HttpServletRequest request)
			throws Exception {
		Adminuser adminuserLogin = (Adminuser) request.getSession().getAttribute("adminuserLogin");
		if(adminuserLogin==null){
			request.getSession().setAttribute("message","对不起您还没有登录");
			return "admin/index";
		}
		GonggaoExample example = new GonggaoExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		Gonggao gonggao = gonggaoMapper.selectByExample(example).get(0);
		model.addAttribute("gonggao", gonggao);
		return "admin/gonggao/edit";
	}
	
	@RequestMapping("/admin/editGonggao")
	public String editGonggao(Gonggao gonggao,HttpServletRequest request,Model model) throws Exception {
   		gonggao.setTime(new Date());
    		GonggaoExample example = new GonggaoExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(gonggao.getId());
		gonggaoMapper.deleteByExample(example);
		gonggaoMapper.insertSelective(gonggao);
		return "redirect:/admin/adminGonggao_findAllByPage.action?page=1";
	}
	
}
