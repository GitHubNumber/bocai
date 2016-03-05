        (function($){
		function getParent(obj,cnt){
			for(var i=0;i<cnt;i++){
				obj = obj.parent();
			}
			return obj;
		}
		
		$.fn.TDragDrop = function(set){
			var defaults = {
				parent:0,		// 拖动对象，向上n父节点，parent为0时，拖动本身
				opacity:0.3	// 拖动时节点透明度，默认为0.3
			};
			var setting = $.extend(defaults, set);

			this.each(function(){
				var ele = getParent($(this),setting.parent);
				ele.css('position','absolute')
				   .css('top',ele.offset().top)
				   .css('left',ele.offset().left);
				$(this).bind('mousedown',function(event){
					event = event || window.event;
					var self = getParent($(this),setting.parent);
					self.attr('flag',true)
						.attr('oldZIndex',self.css('z-index'))
						.attr('startX',event.clientX - self.offset().left)
						.attr('startY',event.clientY - self.offset().top);
						
					self.css('cursor','move')
						.css('z-index',99999)
						.css('opacity',setting.opacity);
          document.onselectstart=function(){return false};
				}).bind('mouseup mouseout',function(){
					var self = getParent($(this),setting.parent);
					self.css('cursor','auto')
						.css('z-index',self.attr('oldZIndex'))
						.css('opacity','1');
					self.attr('flag',false);
					document.onselectstart=function(){return true};
				}).bind('mousemove',function(event){
					event = event || window.event;
					var self = getParent($(this),setting.parent);
					if(self.attr('flag') === 'true'){
						self.offset({'left':event.clientX - self.attr('startX'),
									 'top':event.clientY - self.attr('startY')});
						if(self.offset().left < 0){
							self.offset({'left':0});
						}else if(self.offset().left > $(window).width() - self.width() - 2){
							self.offset({'left':$(window).width() - self.width() - 2});
						}
						if(self.offset().top < 0){
							self.offset({'top':0});
						}else if(self.offset().top > $(window).height() - self.height() - 2){
							self.offset({'top':$(window).height() - self.height() - 2});
						}
						    		
					}
				});
				
			});
		}
	})(jQuery);

	$(function(){
    // 调用
    var obj = $("#reply");
		obj.TDragDrop({'parent':1});
    
    $('.close,.cancel').click(function(){
      $(this).parent().fadeOut('normal');
    })
      
    openDialog('reply');
	});
	
	function openDialog(id){
    // 居中
    var box = $("#"+id).parent();
    box.fadeIn('normal');
    box.offset({'top':($(window).height()-box.height())/2,
                'left':($(window).width()-box.width())/2});
    $("textarea").val('');
	}