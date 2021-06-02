/*
 *  Copyright 2020-2021 ChangTao
 *  Email changtao6605@gmail.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pers.changtao.codegenerator.common;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 数据分页
 * </p>
 *
 * @Author: Alex
 * @Email: changtao6605@gmail.com
 * @Date: 2021-05-11
 */
public class MyPage<T> {
    private Integer pageTotal;
    private Integer pageSize;
    private Integer pageNum;
    private Integer count;
    private List<T> list;

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 把Page<T>分页记录转化为返回结果
     */
    public static <T> MyPage<T> pageResult(Page<T> pageList) {
        MyPage<T> myPage = new MyPage<>();
        myPage.setCount(Convert.toInt(pageList.getTotal()));
        myPage.setPageTotal(Convert.toInt(pageList.getTotal()/pageList.getSize()+1));
        myPage.setPageNum(Convert.toInt(pageList.getCurrent()));
        myPage.setPageSize(Convert.toInt(pageList.getSize()));
        myPage.setList(pageList.getRecords());
        return myPage;
    }

    /**
     * 把List<T>分页记录转化为返回结果
     */
    public static <T> MyPage<T> pageResult(List<T> pageList) {
        MyPage<T> myPage = new MyPage<>();
        PageInfo<T> pageInfo = new PageInfo<T>(pageList);
        myPage.setCount(Convert.toInt(pageInfo.getTotal()));
        myPage.setPageTotal(Convert.toInt(pageInfo.getTotal()/pageInfo.getSize()+1));
        myPage.setPageNum(Convert.toInt(pageInfo.getPageNum()));
        myPage.setPageSize(Convert.toInt(pageInfo.getSize()));
        myPage.setList(pageInfo.getList());
        return myPage;
    }
}
