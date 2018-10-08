/**
 * Project Name:vr-ctrl
 * File Name:FileInfo.java
 * Package Name:com.sd.vr.ctrl.controller
 * Date:2017年3月3日下午1:51:55
 *
 */

package com.admin.utils;

import java.io.Serializable;

/**
 * ClassName:FileInfo <br/>
 * Function: 文件实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月3日 下午1:51:55 <br/>
 *
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
public class FileInfoParam implements Serializable {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    private String md5;
    private int chunkIndex;
    private String size;
    private String name;
    private String userId;
    private String id;
    private int chunks;
    private int chunk;
    private String lastModifiedDate;
    private String type;
    private String ext;
    private String guid;
    /**
     * 内容编号
     */
    private String nrbh;

    public FileInfoParam() {
    }

    public FileInfoParam(String name, String size, int chunkIndex) {
        this.name = name;
        this.size = size;
        this.chunkIndex = chunkIndex;
    }

    public FileInfoParam(String userId, String id) {
        this.userId = userId;
        this.id = id;
    }

    public FileInfoParam(String md5) {
        this.md5 = md5;
    }

    public FileInfoParam(int chunks, int chunk, String userId, String id, String name, String size, String lastModifiedDate, String type) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.size = size;
        this.chunks = chunks;
        this.chunk = chunk;
        this.lastModifiedDate = lastModifiedDate;
        this.type = type;
    }

    public FileInfoParam(String name, int chunks, String ext, String md5) {
        this.name = name;
        this.chunks = chunks;
        this.ext = ext;
        this.md5 = md5;
    }

    /**
     * 内容编号.
     *
     * @return the nrbh
     * @since JDK 1.7
     */
    public String getNrbh() {
        return nrbh;
    }

    /**
     * 内容编号.
     *
     * @param nrbh the 内容编号 to set
     * @since JDK 1.7
     */
    public void setNrbh(String nrbh) {
        this.nrbh = nrbh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(int chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "name=" + name + "; size=" + size + "; chunkIndex=" + chunkIndex + "; md5=" + md5 + "; userId=" + userId + "; id=" + id + "; chunks=" + chunks + "; chunk=" + chunk
                + "; lastModifiedDate=" + lastModifiedDate + "; type=" + type + "; ext=" + ext;
    }
}
