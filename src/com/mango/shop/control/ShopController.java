package com.mango.shop.control;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.mango.shop.bean.Commodity;
import com.mango.shop.bean.Indent;
import com.mango.shop.service.IShopService;
import com.mango.user.bean.User;
import com.mango.user.service.IServiceUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("shopController")@Scope("prototype")
public class ShopController extends ActionSupport{
	private Map<Object,List<Commodity>> indexMap=new HashMap<Object,List<Commodity>>();
	private Commodity comm=new Commodity();
	@Resource(name="shopService")private IShopService shop=null;
	@Resource(name="serviceuser")private IServiceUser userservice=null;
	private int number=0;//当前页码
	private int count=0;//总记录数
	private int pagecount=0;//总页数
	private int pagetype=2;//分页类型
	
	private List<Commodity> shocar=null;
	private List<Integer> allid=null;
	private int[] shop_id=null; 
	private int flag=0;
	
	private List<Indent> oldbuy=null;
	private int[] shopnum=null;
	private int[] price=null;
	private String[] shopname=null;
	
	private String message="";
	private String title=null;
	private int imagename=1;
	
	/**
	 * 查出首页所有数据
	 * @return
	 */
		public String getShopIndexDate(){
			indexMap.put("interest",shop.selSellSumMore());//首页兴趣
			indexMap.put("face", shop.selAllCommodity(0));//首页头像
			indexMap.put("flow", shop.selAllCommodity(1));//首页鲜花
			indexMap.put("facenew", shop.selTypeMaxNew(0));//首页最新头像
			indexMap.put("flownew", shop.selTypeMaxNew(1));//首页最新鲜花
			return "success";
		}
		
		/**
		 * 全部商品页面分页查询
		 * @return
		 * @throws SQLException
		 */
		public String getMoreShop(){
			indexMap.put("maxgood", shop.selMaxGood());
			System.out.println(shop.selMaxGood());
			if(pagetype>2||pagetype<0){
				pagetype=2;
			}
			if(pagetype==2){
				count=shop.selAllCount();
				indexMap.put("allshop",shop.selAllShop((getAllShop(count)-1)*16));
			}
			if(pagetype==0){
				count=shop.selTypeCount(0);
				indexMap.put("allshop",shop.selTypeShop((getAllShop(count)-1)*16,0));
			}
			if(pagetype==1){
				count=shop.selTypeCount(1);
				indexMap.put("allshop",shop.selTypeShop((getAllShop(count)-1)*16,1));
			}
			return SUCCESS;
		}
		/**
		 * 全部商品分页
		 * @return
		 * @throws SQLException
		 */
		public int getAllShop(int count){
			pagecount=count>16?count/16==0?count/16:count/16+1:1;
			if(number>pagecount){
				number=pagecount;
			}
			if(number<1){
				number=1;
			}
			return number;
		}
		
		/**
		 * 添加商品
		 * @return
		 * @throws SQLException
		 */
		public String addCommodity() throws SQLException{
			allid=(List<Integer>) ActionContext.getContext().getSession().get("allid");
			if(allid==null){
				allid=new ArrayList<Integer>();	
			}
			if(allid.indexOf(comm.getId())<0){
			allid.add(comm.getId());
			}
			ActionContext.getContext().getSession().put("allid", allid);
			return selAllCommodity();
		}
		/**
		 * 查询所有商品
		 * @return
		 * @throws SQLException
		 */
		public String selAllCommodity() throws SQLException{
			if(ActionContext.getContext().getSession().get("allid")!=null){
				shocar=new ArrayList<Commodity>();
			for(int i:(List<Integer>)ActionContext.getContext().getSession().get("allid")){
				shocar.add(shop.selCommodity(i));
			}
			ActionContext.getContext().getSession().put("shopcar", shocar);
			}
			return SUCCESS;
		}
		
		/**
		 * 删除商品
		 * @return
		 * @throws SQLException
		 */
		public String delCommodity() throws SQLException{
			allid=(List<Integer>) ActionContext.getContext().getSession().get("allid");
			if(shop_id!=null){
			for(int i:shop_id){
			allid.remove(allid.indexOf(i));
			}
			if(allid.size()==0){
				ActionContext.getContext().getSession().remove("allid");
			}else{
			ActionContext.getContext().getSession().put("allid", allid);
			}
			}
			return selAllCommodity();
		}
		
		/**
		 * 添加交易记录
		 * @return
		 * @throws SQLException 
		 */
		public String addBuyRecord() throws SQLException{
			User user=(User) ActionContext.getContext().getSession().get("landinguser");
			List<Integer> allid=(List<Integer>) ActionContext.getContext().getSession().get("allid");
			if(user.getPower()!=0){
			
				Map<String,Integer> map=new HashMap<String,Integer>();
				int sum=0;
				for(int i=0;i<allid.size();i++){
					sum=sum+price[i]*shopnum[i];
				}
				int integral=user.getIntegral();
				if(integral>=sum){
				Indent indent=new Indent();
				for(int i=0;i<allid.size();i++){
					indent.setShopid(allid.get(i));
					indent.setShopname(shopname[i]);
					indent.setShopprice(price[i]);
					indent.setUserid(user.getId());
					indent.setShopnum(shopnum[i]);
					indent.setPricesum(price[i]*shopnum[i]);
					indent.setBuytime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
					shop.addBuyRecord(indent);
					Commodity comm=shop.selCommodity(allid.get(i));
					comm.setSellsum(shop.getShopSellsum(allid.get(i))+1);
					shop.updateCommodity(comm);
				}
				
				
				ActionContext.getContext().getSession().remove("allid");
				User userinfo=userservice.selUserInfo(user.getId());
				userinfo.setIntegral((integral-sum));
				}else{
					return "KKBOX";
				}
			
				user.setIntegral(integral-sum);
				imagename=2;
				title="购买成功..";
				message="恭喜你交易成功...<a href='/BBS_EcShop/getIndexCommodity'>返回首页</a>&nbsp<a href='/BBS_EcShop/indent/getAllRecord'>交易记录</a>";
				return "message";
			
			}else{
				return ERROR;
			}	
		}
		/**
		 * 得到分页交易记录
		 */
		public String getAllRecord(){
			if(((User)(ActionContext.getContext().getSession().get("landinguser"))).getPower()!=10){
				return "usernotfound";
			}
			System.out.println("guolaile");
			count=shop.selBuyCount(((User) ActionContext.getContext().getSession().get("landinguser")).getId());
			oldbuy=shop.getAllRecord(((User) ActionContext.getContext().getSession().get("landinguser")).getId(),(getAllCount(count)-1)*10);
			return SUCCESS;
		}
		/**
		 * 交易记录分页方法
		 */
		public int getAllCount(int count){
			pagecount=count>10?count/10==0?count/10:count/10+1:1;
			if(number>pagecount){
				number=pagecount;
			}
			if(number<1){
				number=1;
			}
			return number;
		}
		
		
		
		public Map<Object, List<Commodity>> getIndexMap() {
			return indexMap;
		}
		public void setIndexMap(Map<Object, List<Commodity>> indexMap) {
			this.indexMap = indexMap;
		}
		public Commodity getComm() {
			return comm;
		}
		public void setComm(Commodity comm) {
			this.comm = comm;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getPagecount() {
			return pagecount;
		}
		public void setPagecount(int pagecount) {
			this.pagecount = pagecount;
		}
		public int getPagetype() {
			return pagetype;
		}
		public void setPagetype(int pagetype) {
			this.pagetype = pagetype;
		}
		public void setShop(IShopService shop) {
			this.shop = shop;
		}

		public List<Commodity> getShocar() {
			return shocar;
		}

		public void setShocar(List<Commodity> shocar) {
			this.shocar = shocar;
		}

		public List<Integer> getAllid() {
			return allid;
		}

		public void setAllid(List<Integer> allid) {
			this.allid = allid;
		}

		public int[] getShop_id() {
			return shop_id;
		}

		public void setShop_id(int[] shop_id) {
			this.shop_id = shop_id;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}

		public List<Indent> getOldbuy() {
			return oldbuy;
		}

		public void setOldbuy(List<Indent> oldbuy) {
			this.oldbuy = oldbuy;
		}

		public int[] getShopnum() {
			return shopnum;
		}

		public void setShopnum(int[] shopnum) {
			this.shopnum = shopnum;
		}

		public int[] getPrice() {
			return price;
		}

		public void setPrice(int[] price) {
			this.price = price;
		}

		public String[] getShopname() {
			return shopname;
		}

		public void setShopname(String[] shopname) {
			this.shopname = shopname;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getImagename() {
			return imagename;
		}

		public void setImagename(int imagename) {
			this.imagename = imagename;
		}

		public void setUserservice(IServiceUser user) {
			this.userservice = user;
		}
		
		
}
