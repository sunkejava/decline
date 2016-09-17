;(function(window, document, $){
	

  $(document).ready(function(){

    // Make some selections.
    var $window       = $(window);
    var $imgWrapper   = $('.image-wrapper');
    var $imgs         = $imgWrapper.find("img");
    $(" ").append(data.palette[0].replace(')', ",.7)").replace('rgb', "rgba"));
    //$("*").append("<script src='js/jquery.js'></script><script type='text/javascript' src='js/jquery.adaptive-backgrounds.js'></script><script type='text/javascript' src='js/main.js'></script></head><body><div class='image-wrapper slow'><img src='images/pic.png' data-adaptive-background='1' ></div>")
    $imgs.on('ab-color-found', function(e, data){
      document.write(data.palette[0].replace(')', ",.7)").replace('rgb', "rgba"));
     
    });

    // Run the A.B. plugin.
    $.adaptiveBackground.run({ parent: '1' });
	
  })

})(window, document, jQuery)