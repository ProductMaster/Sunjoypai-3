/**************   TinyMce tools  *******************/
//功能：获取TinyMce编辑器的内容
//参数：editorId——编辑器的id
//返回：内容字符串；如果失败，返回空字符串
function GetTinyMceContent(editorId)
{
    return tinyMCE.getInstanceById(editorId).getBody().innerHTML;
}

//功能：设置TinyMce编辑器的内容
//参数：editorId——编辑器的id
//        content——内容
//返回：（无）
function SetTinyMceContent(editorId,content)
{
    tinyMCE.getInstanceById(editorId).getBody().innerHTML=content;
}