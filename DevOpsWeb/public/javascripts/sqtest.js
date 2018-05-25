var changeFrameHeight = function(){ $("#subframe").height($(this).documentElement.clientHeight);}
window.onresize=function(){changeFrameHeight();}