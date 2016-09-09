function onTop()
{
	document.listForm.pageNo.value=1;
	document.listForm.submit();
}
function onPrev()
{
	document.listForm.pageNo.value=parseInt(document.listForm.pageNo.value)-1;
	document.listForm.submit();
}
function onNext()
{
	document.listForm.pageNo.value=parseInt(document.listForm.pageNo.value)+1;
	document.listForm.submit();
}
function onBottom()
{
	document.listForm.pageNo.value=document.listForm.totalPage.value;
	document.listForm.submit();
}
function onSkipTo()
{
	if(parseInt(document.listForm.custompage.value)<1 || parseInt(document.listForm.custompage.value)>parseInt(document.listForm.totalPage.value)){
			alert("跳转页数无效，请重新输入!");
			return false;
		}
		document.listForm.pageNo.value=document.listForm.custompage.value;
		document.listForm.submit();

}