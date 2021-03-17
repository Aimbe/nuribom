		function addclass(e){
			$('.sub-category-list ul > li > i').removeClass(" sel-category");
			$(e).next().addClass(" sel-category");
			$('.sub-category-list ul > li').removeClass(" current-category");
			$(e).parent().addClass(" current-category");
			
			$('.content-category-left').innerText = $(e).text();
			document.getElementsByClassName("content-category-left").innerText = $(e).text();
			console.log(document.getElementsByClassName("content-category-left"));
          };
          
        

       
        
        
        
        