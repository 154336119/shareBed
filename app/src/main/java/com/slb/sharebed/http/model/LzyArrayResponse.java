/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.slb.sharebed.http.model;

import java.io.Serializable;
import java.util.List;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/28
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class LzyArrayResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;
    private List<T> data;
    public String msg;
    public boolean success;
    @Override
    public String toString() {
        return "LzyResponse{\n" +//
               "\tcode=" + success + "\n" +//
               "\tmsg='" + msg + "\'\n" +//
               "\tdata=" + data + "\n" +//
               '}';
    }
}
