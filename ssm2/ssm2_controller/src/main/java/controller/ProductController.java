package controller;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    /*
    * Model和ModelAndView的区别
    * 1：Model是每一次请求可以自动创建，但是ModelAndView 是需要我们自己去new的
            *Model的使用 2：public String listCategory2(Model model) {
                // 接收查询的信息
                List<Category> cs2= categoryService.list();
                // 封装了查询的数据
                model.addAttribute("test", cs2);
                //重要！！需要给出返回model跳转的路径
                return "listCategory2";
            }

    * */

    //查询全部产品
    @RequestMapping("/findAll")
  public ModelAndView findAll() throws Exception {
      ModelAndView mv=new ModelAndView();
      List<Product> products = productService.findAll();
      //将数据放置到ModelAndView对象中，第二个参数是接收的数据集
      mv.addObject("productList",products);
      //放入jsp路径
      mv.setViewName("product-list");
      //返回的是对象，如果是model则返回要跳转的路径
      return mv;
  }

  //产品添加
  @RequestMapping("/save")
  public String save(Product product) throws Exception {
    productService.save(product);
    return "redirect:findAll";
  }
}
