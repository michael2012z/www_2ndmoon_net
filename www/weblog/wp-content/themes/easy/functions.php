<?php
/* 	Easy Theme's Functions
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/
   
  
  	add_theme_support( 'automatic-feed-links' );
  	register_nav_menus( array( 
    	'main-menu' => "Main Menu",
    	'footer-menu' => "Footer Menu"
	) );


//	Set the content width based on the theme's design and stylesheet.
	if ( ! isset( $content_width ) ) $content_width = 650;



// Load the D5 Framework Optios Page and Meta Page
	if ( !function_exists( 'optionsframework_init' ) ) {
	define( 'OPTIONS_FRAMEWORK_DIRECTORY', get_template_directory_uri() . '/inc/' );
	require_once get_template_directory() . '/inc/options-framework.php'; }
	function easy_ppp() { return ( 'post_type=post&&posts_per_page=2&&ignore_sticky_posts=1' );}
	

// 	Tell WordPress for wp_title in order to modify document title content
	function easy_filter_wp_title( $title ) {
    $site_name = get_bloginfo( 'name' );
    $filtered_title = $site_name . $title;
    return $filtered_title;
	}
	add_filter( 'wp_title', 'easy_filter_wp_title' );
	
	add_editor_style('editor-style.css');

// 	This theme uses Featured Images (also known as post thumbnails) for per-post/per-page Custom Header images
	
	add_theme_support( 'post-thumbnails' );
	set_post_thumbnail_size( 150, 150, true ); // default Post Thumbnail dimensions (cropped)

	// additional image sizes
	// delete the next line if you do not need additional image sizes
	add_image_size( 'category-thumb', 650, 300 ); //300 pixels wide (and unlimited height)
	add_image_size( 'slide-thumb', 930, 350 ); //for featured sliders
		
// 	WordPress 3.4 Custom Background Support	
//	add_theme_support( 'custom-background');
	
// 	WordPress 3.4 Custom Header Support				
	$easy_custom_header = array(
	'default-image'          => get_template_directory_uri() . '/images/logo.png',
	'random-default'         => false,
	'width'                  => 300,
	'height'                 => 50,
	'flex-height'            => false,
	'flex-width'             => false,
	'default-text-color'     => '000000',
	'header-text'            => false,
	'uploads'                => true,
	'wp-head-callback' 		 => '',
	'admin-head-callback'    => '',
	'admin-preview-callback' => '',
	);
	add_theme_support( 'custom-header', $easy_custom_header );


// 	Functions for adding script
	function easy_enqueue_scripts() {
	wp_enqueue_style('easy-style', get_stylesheet_uri(), false); 
	if ( is_singular() && comments_open() && get_option( 'thread_comments' ) ) { 
		wp_enqueue_script( 'comment-reply' ); 
	}
	
	wp_enqueue_script( 'jquery');
	wp_enqueue_style('easy-gfonts', '//fonts.googleapis.com/css?family=Economica', false );
	
	global $is_IE;
	if ( $is_IE ) {
    wp_enqueue_script('ie7style', get_template_directory_uri() . '/js/html5.js');
	}
    
	if (is_front_page()):
	wp_enqueue_script( 'easy-slider', get_template_directory_uri() . '/js/slider.js' );
	endif;
	
	}
	add_action( 'wp_enqueue_scripts', 'easy_enqueue_scripts' );

//Multi-level pages menu  
function easy_page_menu() {
	echo '<ul class="m-menu">'; wp_list_pages(array('sort_column'  => 'menu_order, post_title', 'title_li'  => '' )); echo '</ul>';
}


//	function tied to the excerpt_more filter hook.
	function easy_excerpt_length( $length ) {
	global $EasyExcerptLength;
	if ($EasyExcerptLength) {
    return $EasyExcerptLength;
	} else {
    return 50; //default value
    } }
	add_filter( 'excerpt_length', 'easy_excerpt_length', 999 );
	
	function easy_excerpt_more($more) {
       global $post;
	return '<a href="'. get_permalink($post->ID) . '" class="read-more">Read More</a>';
	}
	add_filter('excerpt_more', 'easy_excerpt_more');
	
// Content Type Showing
	function easy_content() { the_content('<span class="read-more">Read More</span>'); }

//	Get our wp_nav_menu() fallback, wp_page_menu(), to show a home link
	function easy_page_menu_args( $args ) {
	$args['show_home'] = true;
	return $args;
	}
	add_filter( 'wp_page_menu_args', 'easy_page_menu_args' );


//	Registers the Widgets and Sidebars for the site
	function easy_widgets_init() {

	
	register_sidebar( array(
		'name' => 'Main Sidebar',
		'id' => 'sidebar-1',
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget' => "</aside>",
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	
	register_sidebar( array(
		'name' => 'Footer Area One',
		'id' => 'sidebar-3',
		'description' => 'An optional widget area for your site footer',
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget' => "</aside>",
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	register_sidebar( array(
		'name' => 'Footer Area Two',
		'id' => 'sidebar-4',
		'description' => 'An optional widget area for your site footer',
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget' => "</aside>",
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );

	register_sidebar( array(
		'name' => 'Footer Area Three',
		'id' => 'sidebar-5',
		'description' => 'An optional widget area for your site footer',
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget' => "</aside>",
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
	
	register_sidebar( array(
		'name' => 'Footer Area Four',
		'id' => 'sidebar-6',
		'description' => 'An optional widget area for your site footer',
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget' => "</aside>",
		'before_title' => '<h3 class="widget-title">',
		'after_title' => '</h3>',
	) );
		
	}
	add_action( 'widgets_init', 'easy_widgets_init' );
	
		
// 	When the post has no post title, but is required to link to the single-page post view.
	add_filter('the_title', 'easy_title');
	function easy_title($title) {
        if ( '' == $title ) {
            return '(Untitled)';
        } else { return $title; } 
    }

	add_filter( 'wp_nav_menu_objects', 'easy_add_menu_parent_class' );
function easy_add_menu_parent_class( $easyitems ) {
	
	$easyparents = array();
	foreach ( $easyitems as $easyitem ) {
		if ( $easyitem->menu_item_parent && $easyitem->menu_item_parent > 0 ) {
			$easyparents[] = $easyitem->menu_item_parent;
		}
	}
	
	foreach ( $easyitems as $easyitem ) {
		if ( in_array( $easyitem->ID, $easyparents ) ) {
			$easyitem->classes[] = 'menu-parent-item'; 
		}
	}
	
	return $easyitems;    
}

//	Remove WordPress Custom Header Support for the theme easy
//	remove_theme_support('custom-header');