package com.base.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("file")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    //文件路径
    String path = "F:\\dev_file\\upload\\";
	
    @RequestMapping("index")
    public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/base/module/upload/index");
		return modelAndView;
	}
    @RequestMapping("onefile")
    public ModelAndView onefile() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("/base/module/upload/include/onefile");
    	return modelAndView;
    }
    @RequestMapping("mulfile")
    public ModelAndView mulfile() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("/base/module/upload/include/mulfile");
    	return modelAndView;
    }
    //单文件上传
	@PostMapping("oneupload")
	public String upload(@RequestParam("file") MultipartFile file) { 
	    //判断非空
	    if (file.isEmpty()) {
	        return "上传的文件不能为空";
	    }
	    try {
	        // 测试MultipartFile接口的各个方法
	        logger.info("[文件类型ContentType] - [{}]",file.getContentType());
	        logger.info("[文件组件名称Name] - [{}]",file.getName());
	        logger.info("[文件原名称OriginalFileName] - [{}]",file.getOriginalFilename());
	        logger.info("[文件大小] - [{}]",file.getSize());
	        logger.info(this.getClass().getName()+"图片路径："+path);
	        File f = new File(path);
	        //如果不存在该路径就创建
	        if (!f.exists()) {
	            f.mkdir();
	        }
	        File dir = new File(path + file.getOriginalFilename());
	        //文件写入
	        file.transferTo(dir);
	        return "上传单个文件成功";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "上传单个文件失败";
	    }
	}
	//io流上传,但是字节流比较慢
	public void writeFile(MultipartFile file) {
	    try {
	        //获取输出流
	        OutputStream os = new FileOutputStream(path + file.getOriginalFilename());
	        //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
	        InputStream is = file.getInputStream();
	        byte[] buffer = new byte[1024];
	        //判断输入流中的数据是否已经读完的标识
	        int length = 0;
	        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
	        while((length = is.read(buffer))!=-1){
	            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
	            os.write(buffer, 0, length);
	        }
	        os.flush();
	        os.close();
	        is.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//多文件上传
	@PostMapping("/mulupload")
	public String uploadBatch(@RequestParam("files") MultipartFile[] files) {
	    logger.info("文件名称："+ files );
	    if(files!=null&&files.length>0){
	        for (MultipartFile mf : files) {
	            // 获取文件名称
	            String fileName = mf.getOriginalFilename();
	            // 获取文件后缀
	            String suffixName = fileName.substring(fileName.lastIndexOf("."));
	            // 重新生成文件名
	            fileName = UUID.randomUUID()+suffixName;
	            if (mf.isEmpty()) {
	                return "文件名称："+ fileName +"上传失败，原因是文件为空!";
	            }
	            File dir = new File(path + fileName);
	            try {
	                // 写入文件
	                mf.transferTo(dir);
	                logger.info("文件名称："+ fileName +"上传成功");
	            } catch (IOException e) {
	                logger.error(e.toString(), e);
	                return "文件名称："+ fileName +"上传失败";
	            }
	        }
	        return "多文件上传成功";
	    }
	    return "上传文件不能为空";
	}
	
	//文件下载
	@GetMapping("/downloadfile")
	public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	    String fileName = "img.jpg";// 文件名

	    if (fileName != null) {
	        //设置文件路径
	        File file = new File(path+"img.jpg");
	        //File file = new File(realPath , fileName);
	        if (file.exists()) {
	            response.setContentType("multipart/form-data");
	            response.setHeader("Content-Disposition", "attachment; fileName="+  fileName 
	            		+";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
	            byte[] buffer = new byte[1024];
	            FileInputStream fis = null;
	            BufferedInputStream bis = null;
	            try {
	                fis = new FileInputStream(file);
	                bis = new BufferedInputStream(fis);
	                OutputStream os = response.getOutputStream();
	                int i = bis.read(buffer);
	                logger.info(""+i);
	                while (i != -1) {
	                    os.write(buffer, 0, i);
	                    i = bis.read(buffer);
	                }
	                return "下载成功";
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                if (bis != null) {
	                    try {
	                        bis.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	                if (fis != null) {
	                    try {
	                        fis.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    }
	    return "文件不存在";
	}
}
