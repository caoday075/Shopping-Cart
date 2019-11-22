// ------------Chỗ Logout-----------
$(document).ready(function(){    
    $('#showDropdown_da').click( function(e) {        
        e.preventDefault(); // stops link from making page jump to the top
        e.stopPropagation(); // when you click the button, it stops the page from seeing it as clicking the body too
        $('#wrap_drop_da').toggle();        
    });    
    $('#wrap_drop_da').click( function(e) {        
        e.stopPropagation(); // when you click within the content area, it stops the page from seeing it as clicking the body too
    });    
    $('body').click( function() {       
        $('#wrap_drop_da').hide();        
    });    
});



// ----------Left Menu---------
$('.menu_da').on('click', function(){   
    $('.menu_da').removeClass('selected_da');
    $(this).addClass('selected_da');        
});

$('.menu_da').on('click', function(){
    $('.menu_da').removeClass('selectedBackground_da');
    $(this).addClass('selectedBackground_da');     
});


// -------SHOWDROPDOWN------
var dropdown = document.getElementsByClassName("menu2_da");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
    // this.classList.toggle("active");
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
     // dropdownContent.style.top = "-50px";
     //  dropdownContent.style.transitionDelay = "0.5s";
     //  dropdownContent.style.transitionDuration = "1s";
     //  dropdownContent.style.transitionProperty = "top";
    
    } else {
      dropdownContent.style.display = "block";
      // dropdownContent.style.top = "0";
      // dropdownContent.style.transitionDelay = "0.5s";
      // dropdownContent.style.transitionDuration = "1s";
      // dropdownContent.style.transitionProperty = "top";
    }
  });
}

var dropdown1 = document.getElementsByClassName("menu3_da");
var j;

for (j = 0; j < dropdown1.length; j++) {
  dropdown1[j].addEventListener("click", function() {   
    var dropdown1Content = this.nextElementSibling;
    if (dropdown1Content.style.display === "block") {
      dropdown1Content.style.display = "none";    
    } else {
      dropdown1Content.style.display = "block";      
    }
  });
}


    // transition-timing-function: ease;
// $(document).ready(function() {
//     $('.left_menu_da > li > a').click(function(event){
//         event.preventDefault();//stop browser to take action for clicked anchor
                    
//         //get displaying tab content jQuery selector
//         var active_tab_selector = $('.left_menu_da > li.active_da > a').attr('href');                  
                    
//         //find actived navigation and remove 'active' css
//         var actived_nav = $('.left_menu_da > li.active_da');
//         actived_nav.removeClass('active_da');
                    
//         //add 'active' css into clicked navigation
//         $(this).parents('.menu_da').addClass('active_da');
                    
//         //hide displaying tab content
//         $(active_tab_selector).removeClass('active_da');
//         $(active_tab_selector).addClass('hide_da');
                    
//         //show target tab content
//         var target_tab_selector = $(this).attr('href');
//         $(target_tab_selector).removeClass('hide_da');
//         $(target_tab_selector).addClass('active_da');
//         });    
//     });



/*Khung Edit*/
 $(document).ready(function() {
    $('#new_text_da').summernote();
    $('#event_text_da').summernote();
});

// var width = $(window).width() {
// if (width < 841) {
//   $(".first_overview_da").css("width": "100% !important");  
//   $(".fourth_overview_da").css("width": "100% !important");
  
// }}
// --------------VIEW CREATE-----------
// $(document).ready(function(){    
//     $(".display_create").click(function(){
//         $(".view_create").show();
//     });
//     $(".hide_create").click(function(){
//         $(".view_create").hide();
//     });
// });

// --------------VIEW TABLE-----------
// $(document).ready(function(){    
//     $(".display_table").click(function(){
//         $(".table_da").show();
//     });
//     $(".hide_table").click(function(){
//         $(".table_da").hide();
//     });
// });


$(document).ready(function() {
  function counter(element, count, time){
    time = time || 1000;
    var actualCounter = 0;
    function startCount(){
      actualCounter++;
      element.text(parseInt(actualCounter/time * count));
      if(actualCounter != time){
        setTimeout(startCount, 1)
      }
    }
    startCount();
    
  }
  $('.start-counter').each(function(){
    var $this = $(this);
    var count = parseInt($this.text());
    counter($this, count);
    
  });
  
  var dataset1 = [1, 10, 5, 2];
  var dataset2 = [20, 30, 15, 12];
  var dataset3 = {
    datasets: [{
      data: [70, 30],
      backgroundColor: [
        "#FF6B6B",
        "#C44D58",
      ],
    }],

    // These labels appear in the legend and in the tooltips when hovering different arcs
    labels: [
        'data 1',
        'data 2'
    ],
    
  };
  
  var ctxLine = $('#line-chart');
  var ctxPie = $('#pie-chart');
  var myLineChart = new Chart(ctxLine, {
    type: 'line',
    options: {
      legend: {
          display: false
      },
      aspectRatio: 4
    },
    data: {
      labels: ["January", "February", "March", "April"],
        datasets: [
          {
            label: "Dataset 1!",
            fill: false,
            backgroundColor: 'rgb(190, 99, 255, 0.25)',
            borderColor: 'rgb(190, 99, 255)',
            data: dataset1,
          },
            {
            label: "Dataset 2!",
            fill: false,
            backgroundColor: 'rgba(255, 99, 132, 0.25)',
            borderColor: 'rgb(255, 99, 132)',
            data: dataset2,
        }]
    }
  });
  
  var myLineChart = new Chart(ctxPie, {
    type: 'pie',
    options: {
      legend: {
          display: false
      },
      aspectRatio: 4
    },
    data: dataset3
  });
  
  $(document).on('click', '.stickynote-card .colors .color', function() {
    var $this = $(this);
    $this.addClass('active').siblings().removeClass('active');
    $this.parents('.stickynote-card').attr('class', 'stickynote-card style-'+ $this.data('color'));
  });
});