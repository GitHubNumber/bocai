package com.mango.blogs.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mango.bean.PromptResult;
import com.mango.bean.QueryResult;
import com.mango.blogs.bean.BlogsComment;
import com.mango.blogs.bean.BlogsPhoto;
import com.mango.blogs.bean.BlogsPhotoGroup;
import com.mango.blogs.bean.BlogsPhotoUpload;
import com.mango.blogs.service.IServiceBlogsComment;
import com.mango.blogs.service.IServiceBlogsPhoto;
import com.mango.blogs.service.IServiceBlogsPhotoGroup;
import com.mango.user.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 博客相册管理
 * 
 * @author lyy
 */
@Controller
@Scope("prototype")
public class ControllerBlogsPhoto extends ActionSupport {
	public static final int pageSize = 12;
	private final int photoType = 3;
	private static final long serialVersionUID = 1L;
	/**
	 * 相册管理action
	 */
	@Resource(name = "serviceblogsphotogroup")
	private IServiceBlogsPhotoGroup serviceblogsphotogroup = null;
	@Resource(name = "serviceblogsphoto")
	private IServiceBlogsPhoto serviceblogsphoto = null;
	@Resource(name = "serviceblogscomment")
	private IServiceBlogsComment serviceBlogsComment = null;
	@PersistenceContext protected EntityManager em;
	private BlogsPhotoGroup blogsphotogroup = null;
	
	private User user;
	
	private BlogsPhoto blogsphoto = null;
	private BlogsPhotoUpload blogsPhotoUpload = null;
	private List<BlogsPhoto> list = null;
	private List<BlogsPhotoGroup> glist = null;
	private List<BlogsComment> clist = null;
	//提示类...
	private PromptResult pr = null;
	//旗标，0代表上一张，1代表下一张
	int flag ;
	// 分页的num
	private int pageNum;
	// 最大页数
	private int maxPageNum;
	// 总记录数
	private int allNum;
	
	/**
	 * 分页查询相册
	 * select * from photo where group_id =1;
	 * 根据
	 * @return
	 */
	public String selectPhotoByGroupId() {
		 //根据从jsp传过来的photoGroup.id的值获取的photo list ,查询个数为pageSize 
		 //最后为user_id
		 QueryResult<BlogsPhoto> qr = serviceblogsphoto.selectPhoto(this.photoPageNum(),"group_id = "+blogsphotogroup.getId()+" and user_id = "+ user.getId());
		 this.setList(qr.getResultlist());
		 //第一个参数是从哪里开始,第二个是user_id
		QueryResult<BlogsPhotoGroup> qr2 = serviceblogsphotogroup.selectPhotoGroup(0,user.getId());
		this.setGlist(qr2.getResultlist());
		return "list";
	}
	
	public String selectMyPhoto(){
		//通过blogsphoto.id查询出指定photo
		String hql="id = "+ blogsphoto.getId()+" and user_id = "+ user.getId();
		if(flag==-1){
			hql="id < "+ blogsphoto.getId()+" and user_id = "+ user.getId()+"order by id desc";
		}else if(flag==1){
			hql="id > "+ blogsphoto.getId()+" and user_id = "+ user.getId();
		}
		try {
			QueryResult<BlogsPhoto> qr = serviceblogsphoto.selectPhotoById(hql);
			this.setBlogsphoto(qr.getResultlist().get(0));
		} catch (Exception e) {
			QueryResult<BlogsPhoto> qr = serviceblogsphoto.selectPhotoById("id = "+ blogsphoto.getId()+" and user_id = "+ user.getId());
			this.setBlogsphoto(qr.getResultlist().get(0));
		}
		
		//通过photo表的id查询指定photo信息
		//3为类型,即评论类型为photo,第二个参数为user_id
		QueryResult<BlogsComment> qr2 = serviceBlogsComment.selectAll(photoType, user.getId(), blogsphoto.getId());
		this.setClist(qr2.getResultlist());
		return  "myphoto" ;
	}
	
	/**
	 * 获取分页查询的pageNum
	 */
	private int photoPageNum() {
		return (this.getPageNum() - 1) * pageSize;
	}

	/**
	 * 添加照片
	 * @param photo
	 * @return
	 * @throws IOException 
	 */
	public String addPhoto() throws IOException {
		User landinguser=(User) ActionContext.getContext().getSession().get("landinguser");
		if(landinguser.getId()==null){
			return "error";
		}
		
		//用事物这种模式写最好了
		FileOutputStream fos = null;
		FileInputStream fis = null;
		//实例化输出流,输出到哪里去.
			try {
				blogsPhotoUpload.setUploadFileName(landinguser.getId().toString()+"_"+blogsPhotoUpload.getUploadFileName());
				fos = new FileOutputStream(blogsPhotoUpload.getSavePath()+"\\"+blogsPhotoUpload.getUploadFileName());
				fis=new FileInputStream(blogsPhotoUpload.getUpload());
				byte[] buffer=new byte[1024];
				int len = 0;
				while((len=fis.read(buffer))>0){
					fos.write(buffer, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//如果上传的文件不符合规范,跳转到error页面
				return "error";
			}
		fos.close();
		fis.close();
		BlogsPhoto bp = new BlogsPhoto();
		bp.setDate(new Date(System.currentTimeMillis()));
		//放到那个组里
		bp.setGroup_id(this.getBlogsphotogroup().getId());
		bp.setPhoto(blogsPhotoUpload.getUploadFileName());
		if(blogsPhotoUpload.getInfo()==null||blogsPhotoUpload.getInfo()==""){
			blogsPhotoUpload.setInfo("无相片描述");
		}
		bp.setPhotoInfo(blogsPhotoUpload.getInfo());
		
		User u = new User();
		u.setId(landinguser.getId());
		this.setUser(u);
		bp.setUser_id(landinguser.getId());
		serviceblogsphoto.addPhoto(bp);
		this.selectPhotoByGroupId();
		return "addphoto";
	}
	/**
	 * 按photoId删除指定的日志
	 * 
	 * @return
	 */
	public String delectLogById() {
		//删除指定id的blogsphoto
		if(serviceblogsphoto.delectPhoto(blogsphoto)==1){
				// 添加完后返回日志列表,使用个头getFenYe方法
				this.selectPhotoByGroupId();
				return "list";
			} else {
				return "error";
			}
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String togoAdd(){
		
		this.setBlogsphotogroup(this.getBlogsphotogroup());
		return "togoAdd";
	}
	public PromptResult getPr() {
		return pr;
	}

	public void setPr(PromptResult pr) {
		this.pr = pr;
	}

	public int getPageNum() {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getMaxPageNum() {

		return maxPageNum;
	}

	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public BlogsPhotoGroup getBlogsphotogroup() {
		return blogsphotogroup;
	}

	public void setBlogsphotogroup(BlogsPhotoGroup blogsphotogroup) {
		this.blogsphotogroup = blogsphotogroup;
	}

	public IServiceBlogsPhoto getServiceblogsphoto() {
		return serviceblogsphoto;
	}

	public void setServiceblogsphoto(IServiceBlogsPhoto serviceblogsphoto) {
		this.serviceblogsphoto = serviceblogsphoto;
	}

	public BlogsPhoto getBlogsphoto() {
		return blogsphoto;
	}

	public void setBlogsphoto(BlogsPhoto blogsphoto) {
		this.blogsphoto = blogsphoto;
	}

	public List<BlogsPhoto> getList() {
		return list;
	}

	public void setList(List<BlogsPhoto> list) {
		this.list = list;
	}

	public BlogsPhotoUpload getBlogsPhotoUpload() {
		return blogsPhotoUpload;
	}

	public void setBlogsPhotoUpload(BlogsPhotoUpload blogsPhotoUpload) {
		this.blogsPhotoUpload = blogsPhotoUpload;
	}

	public List<BlogsPhotoGroup> getGlist() {
		return glist;
	}

	public void setGlist(List<BlogsPhotoGroup> glist) {
		this.glist = glist;
	}

	public List<BlogsComment> getClist() {
		return clist;
	}

	public void setClist(List<BlogsComment> clist) {
		this.clist = clist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
