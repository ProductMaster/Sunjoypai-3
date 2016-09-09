var h_chargeSelected = 0;
var h_selects = "";
var h_selects_length = 0;

function be_selected(selectid){
 if ((document.getElementById(selectid).selectedIndex)==-1){
     	setArrow("none","block","none","block");
 }
 option_length = document.getElementById(selectid).length;
 if ((document.getElementById(selectid).selectedIndex)<(option_length) && (document.getElementById(selectid).selectedIndex)!=-1){
   var option_length;
   if (option_length >0 ){
       if (selectid == "original_list"){
          document.getElementById("selected_list").selectedIndex=-1;
          setArrow("none","block","block","none");
       }
      if (selectid == "selected_list"){
          document.getElementById("original_list").selectedIndex=-1;
          setArrow("block","none","none","block");
       }
     }
   }
}
function setArrow(leftStyle,leftDisabled,rightStyle,rightDisabled){
        document.getElementById("arrowRight_disabled").style.display = rightDisabled;
        document.getElementById("arrowRight").style.display          = rightStyle;
        document.getElementById("arrowLeft_disabled").style.display  = leftDisabled;
        document.getElementById("arrowLeft").style.display           = leftStyle;
}
function do_shift(source,target){
  var tempsource = document.getElementById(source);
  var temptarget = document.getElementById(target);
  var lastItem ;
//  if (source == "selected_list")
//       {
           h_chargeSelected = 1;
//       }

   if ((tempsource.selectedIndex)<=(tempsource.length-1) && (tempsource.selectedIndex)!=-1){
      for ( var j=(tempsource.length-1);j>=0;j--){
          if (tempsource.options[j].selected){
             temptarget.length++;
             temptarget.options[temptarget.length-1].value=tempsource.options[j].value;
             temptarget.options[temptarget.length-1].text=tempsource.options[j].text;  //tempsource.selectedIndex
              for(i=j;i<(tempsource.length-1);i++){  //tempsource.selectedIndex
                   tempsource.options[i].value=tempsource.options[i+1].value;
                   tempsource.options[i].text=tempsource.options[i+1].text;
              }
              tempsource.length--;
              lastItem = j;
        }
    }
      if (lastItem >= tempsource.length ) lastItem = tempsource.length - 1;
      if (tempsource.length != 0 ) tempsource.options[lastItem].selected = "true";
//      	else {
//              	if (temptarget.length != 0 ) temptarget.options[temptarget.length - 1].selected = "true";
//          }
    }
//			tempsource.fireEvent("onchange");
    var tempstring = "be_selected('"+source+"')";
    eval(tempstring);
}

function showConfirm(){

}
function setSelected(){
    var selects = "";
    var selected_role = document.getElementById("selected_list");
    //alert("select_list_length:"+selected_role.legnth);
    for (i = 0;i<selected_role.length;i++){
        selects = selects+selected_role.options[i].value+":";
	}
    h_selects = selects;
    h_selects_length = selected_role.length;
    //alert("h_selects:"+h_selects);
    //alert("h_selects_length:"+h_selects_length);
}
function isChangeSelected(){
    return h_chargeSelected;
}

