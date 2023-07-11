package com.ershouserver.ershou_server.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ershouserver.ershou_server.Utils.DateCalculater;
import com.ershouserver.ershou_server.Utils.FtpTransformFile;
import com.ershouserver.ershou_server.common.Result;
import com.ershouserver.ershou_server.entity.Good;
import com.ershouserver.ershou_server.mapper.GoodMapper;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GoodService extends ServiceImpl<GoodMapper, Good> {

    @Autowired
    private GoodMapper goodMapper;

    //处理files的每一个，并把它拼接起来转为json，赋值给good的media
    public Good saveAndTransformFiles(Good good,List<MultipartFile> files){
        StringBuilder builder = new StringBuilder();

        try {
            // 检查目录是否存在，不存在则创建
            // 遍历文件数组，逐个保存文件
            for (MultipartFile file : files) {


                String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileEndName=new SimpleDateFormat("yyyyMMdd").format(new Date())+"_"+good.getContactway() +
                        "_"+System.currentTimeMillis()+fileType;
                String fileUrl = "\"http://8.130.40.208:777/images/"
                        +fileEndName+"\"";
                File okFile = FtpTransformFile.convertMultipartFileToFile(file, fileEndName);
                //通过获取BufferedImage的方式来完成：
                // 如果宽高比不是4/3，就旋转90度，
                //让图片压缩为400*300
                BufferedImage image = Thumbnails.of(okFile).scale(0.6).asBufferedImage();
                System.out.println("image========="+image);
                int width = image.getWidth();
                int height = image.getHeight();
                double ratio = (double) width / height;
                if (ratio <1.0) {
                    Thumbnails.of(image)
                            .scale(0.2)
                            .rotate(-90)
                            .outputQuality(1.0f)
                            .toFile(okFile);
                }
                    Thumbnails.of(image)
                            .scale(0.2)
                            .outputQuality(1.0f)
                            .toFile(okFile);
                FtpTransformFile.SftpUploadToServer(okFile);
                builder.append(fileUrl).append(",");

            }
            builder.insert(0, "[");
            builder.append("]");
            builder.deleteCharAt(builder.length()-2 );
            String media=builder.toString();
            good.setMedia(media);
        } catch (IOException | JSchException | SftpException e) {
            e.printStackTrace();
        }
        return good;
    }
    //提交表单，进数据库
    public Result SaveForm(Good good) {

        good.setAddtime(new Date());//这里手动添加的addtime
        if(this.save(good)){
            return Result.success(good);
        }
        return Result.error();
    }

    //随机获取8条记录
    public List<Good> getRandomGoods() {
        List<Good> Goods = goodMapper.selectSomeGoods();
        return Goods;

    }

    public List<Good> QuerySearch(String SearchValue,Integer timeValue,
                                  Integer zongheValue,
                                  Boolean switch1,String radio) {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        System.out.println(radio);
        wrapper.isNotNull("media")
                .like("name",SearchValue);
        if (goodMapper.selectList(wrapper).size()==0){//如果name为0，那么就查看英文匹配的
                wrapper.clear();
                wrapper.apply("((TotalPinyin(`name`))) like '%"+SearchValue+"%'");
                return goodMapper.selectList(wrapper);
        }

        if(zongheValue==0&&timeValue==3&&switch1==false&&radio.equals("全校")){
            return goodMapper.selectList(wrapper);
        }
        else{
            if(!radio.equals("全校")){
                wrapper.like("address",radio);
            }
            if(zongheValue==1){
                wrapper.orderByAsc("price");
            }
            if(zongheValue==2){
                wrapper.orderByDesc("price");
            }
            if(timeValue==4){
                wrapper.ge("addtime", DateCalculater.GetNeedTimeDistance(-7));
            }
            if(timeValue==5){
                wrapper.ge("addtime", DateCalculater.GetNeedTimeDistance(-30));
            }

        }

        return goodMapper.selectList(wrapper);
    }

    public Good QueryByID(Long id) {
        Good good = goodMapper.selectById(id);
        System.out.println(good.getMedia());
        return good;
    }
}
