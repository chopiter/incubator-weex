/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.weex.dom.action;

import android.text.TextUtils;

import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.Map;


/**
 * Created by boze on 2017/5/7.
 */

public class ActionUtils {

  public static final boolean isVirtualSwitch = true;//为true打开
  public static final boolean styleVirtualSwitch = true;//true 打开

  public static boolean needMarkVirtual(WXComponent component, Map<String, Object> style){
    if(!isVirtualSwitch){
      return false;
    }
    //终极父控件是div,本身是div
    WXVContainer parent = component.getParent();
    while (parent != null && parent.isVirtual()){
      parent = parent.getParent();
    }
    if(parent != null && isDiv(parent) && isDiv(component)){
      //无背景
      if(style == null || style.get(Constants.Name.BACKGROUND_COLOR) == null
          || TextUtils.isEmpty((CharSequence) style.get(Constants.Name.BACKGROUND_COLOR))){
        //无事件
        if(component.getDomObject().getEvents().size() == 0){
          return true;
        }
      }
    }

    return false;
  }

  public static boolean needMarkVirtual(WXComponent component){
    //终极父控件是div,本身是div
    WXVContainer parent = component.getParent();
    while (parent != null && parent.isVirtual()){
      parent = parent.getParent();
    }
    if(parent != null && isDiv(parent) && isDiv(component)){
      //无背景
      WXStyle style = component.getDomObject().getStyles();
      if(style.getBackgroundColor().equals("")){
        //无事件
        if(component.getDomObject().getEvents().size() == 0){
          return true;
        }
      }
    }

    return false;
  }

  private static boolean isDiv(WXComponent component){
    if(component != null && component.getClass().getName().equals(WXDiv.class.getName())){
      return true;
    }
    return false;
  }

}
