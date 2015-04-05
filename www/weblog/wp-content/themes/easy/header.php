<?php

/* 	Easy Theme's Header
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/

?>

<!DOCTYPE html>
<html <?php language_attributes(); ?>>
<head>
<meta charset="<?php bloginfo( 'charset' ); ?>" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><?php wp_title() ?></title>
<link rel="profile" href="http://gmpg.org/xfn/11" />
<link rel="pingback" href="<?php bloginfo( 'pingback_url' ); ?>" />

<?php  wp_head(); ?>

</head>

<body <?php body_class(); ?> >
  	  <div id="top-menu-container" >
      <!-- Site Titele and Description Goes Here -->
	<!-- Site Titele and Description Goes Here -->
    <a href="<?php echo esc_url( home_url( '/' ) ); ?>"><img class="site-logo" src="<?php header_image(); ?>"/></a> 
    <h2 class="site-title-hidden"><p><?php bloginfo( 'description' ); ?></p></h2>
      
      <!-- Site Main Menu Goes Here -->
	<div id="main-menu">
	<?php wp_nav_menu( array( 'theme_location' => 'main-menu', 'menu_class' => 'm-menu', 'fallback_cb' => 'easy_page_menu'  )); ?>  
    </div></div><div class="clear"></div>
 	<h2 class="site-title-hidden"><p><?php bloginfo( 'description' ); ?></p></h2>
    
      
          
      
	  
	 
	  