var tfConfig = {
	base_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/',
	// col_2: 'select',
 //    col_2: 'checklist',
   
    filters_row_index: 1,
    alternate_rows: true,
    rows_counter: true,
    btn_reset: true,
    loader: true,
    status_bar: true,
    col_types: [
        'string',
        'string',
        'string',
        'string',
        'String'        
          
    ],
    // optional as filter options are sorted
    // in ascending manner by default
    // sort_filter_options_asc: [0, 3, 6],
    sort_filter_options_desc: [4, 5, 7],
    col_widths: [
//      '150px', '75px', '90px',
//      '110px', '110px',
//      '*'
    ],
    extensions:[{ 
      name: 'sort',
      images_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/style/themes/' 
    }]
};

var tf = new TableFilter('filter-order', tfConfig);
tf.init();