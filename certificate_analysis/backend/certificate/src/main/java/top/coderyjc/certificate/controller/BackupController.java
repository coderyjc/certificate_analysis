package top.coderyjc.certificate.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.ss.formula.functions.MatrixFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.coderyjc.certificate.model.entity.Backup;
import top.coderyjc.certificate.service.IBackupService;
import top.coderyjc.certificate.util.DateUtil;
import top.coderyjc.certificate.util.Msg;

import javax.management.Query;
import java.io.File;

/**
 * <p>
 * 数据库备份和恢复 前端控制器
 * </p>
 *
 * @author Yan Jingcun
 * @since 2023-04-22
 */
@RestController
@RequestMapping("/backup")
public class BackupController {

    @Autowired
    IBackupService service;

    /**
     * 获取所有备份数据
     * @param pn
     * @param size
     * @param table
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Msg listAll(
            @RequestParam(value = "current", defaultValue = "1") Integer pn,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "table", defaultValue = "") String table
    ){
        QueryWrapper<Backup> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("backup_date");
        IPage<Backup> iPage = new Page<>(pn, size);
        if(!table.equals("")) wrapper.eq("table_name", table);
        IPage<Backup> data = service.getBaseMapper().selectPage(iPage, wrapper);
        return Msg.success().add("data", data);
    }

    /**
     * 进行备份
     * @param tableName
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Msg backupItem(
            @RequestParam(value = "table_name")String tableName,
            @RequestParam(value = "description") String description
            ){
        service.backup(tableName, description);
        return Msg.success();
    }

    /**
     * 删除备份
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Msg deleteItem(@RequestParam(value = "id")Integer id){
//        获取备份数据
        Backup backup = service.getById(id);
        File file = new File("./mysql_backup/" + backup.getFilename());
        boolean rst = false;
        if(file.exists()) rst = file.delete();
        rst = service.removeById(id) && rst;
        return Msg.success().add("data", rst ? "删除成功" : "删除失败");
    }


    /**
     * 恢复数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public Msg recover(@RequestParam(value = "id")Integer id){
        System.out.println(id);
        service.recover(id);
        return Msg.success();
    }

}
