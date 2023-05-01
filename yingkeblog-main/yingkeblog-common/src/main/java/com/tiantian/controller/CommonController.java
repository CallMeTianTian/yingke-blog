package com.tiantian.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tiantian.mapper.UserMapper;
import com.tiantian.pojo.Result;
import com.tiantian.utils.AliOSSUtils;
import com.tiantian.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * <h1>文件的上传和下载</h1> 必须携带token才能进行访问<br/>
 * 两套方案 => 分别为通过IO流进行本地存储和使用阿里云的OOS免费服务进程存储(推荐)
 */
@RestController
@RequestMapping("/common")
@CrossOrigin
@SuppressWarnings("all")
@Slf4j
public class CommonController {

    /**
     * <h1>本地存储</h1>
     */

    @Value("${tiantianstudy.path}")
    private String basePath;

    @PostMapping("/upload")
    @SaCheckLogin
    public Result upload(MultipartFile file) {
        //  获取原始文件名 但可以会导致文件重复进而覆盖文件
        String originalFilename = file.getOriginalFilename();
        // 截取缀
        String suffix = null;
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // 使用UUID 防止重复
        String fileName = UUID.randomUUID() + suffix;

        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // 将临时文件转存在指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("创建文件" + fileName);
        // 拼接URL
        String URL = "http://localhost/common/download/" + fileName;
        return new Result(200, URL);
    }

    @GetMapping("/download/{name}")
    public void download(@PathVariable String name, HttpServletResponse response) {
        System.out.println("图片的名称 = " + name);
        try {
            FileInputStream fileInputStream = new FileInputStream(basePath + name);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");

            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    @DeleteMapping("/delete/{fileName}")
    public Result deleteFile(@PathVariable String fileName) {
        System.out.println("删除文件的名称为: " + fileName);

        try {
            // 拼接路径
            String filePath = basePath + fileName;
            File myDelFile = new File(filePath);
            // 删除指定文件
            boolean delete = myDelFile.delete();
            if (delete) return Result.ok();
            else return Result.failure();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
        return Result.failure();
    }


    /**
     * <h1 style="color: yellow">使用阿里云OOS进行存储</h1>
     */

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/oss/upload")
    @SaCheckLogin
    public Result uploadByAliOOS(MultipartFile file) throws Exception {
        log.info("文件上传, 文件名: {}", file.getOriginalFilename());
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成, 文件访问的url: {}", url);
        // 将用户头像地址写入数据库 并返回给前端
        userMapper.updateHeadUrl(url, UserHolder.getUid());
        return new Result(200, url);
    }

    @DeleteMapping("/oss/delete/{fileName}")
    public Result deleteByFileName(@PathVariable String fileName) {
        System.out.println("删除的文件为: " + fileName);
        Integer code = aliOSSUtils.delFile(fileName);
        if (code == 200) return Result.ok();
        else return Result.failure();
    }

}
