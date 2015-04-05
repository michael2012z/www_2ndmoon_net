<?php
/* Easy Theme's Footer
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/
?>

<div id="footer">

<div id="footer-content">


<?php
   	get_sidebar( 'footer' );
?>
</div> <!-- footer-content -->
</div> <!-- footer -->
</div><!-- container -->
<div id="footermenu"><?php if ( has_nav_menu( 'footer-menu' ) ) {  wp_nav_menu( array( 'theme_location' => 'footer-menu', 'menu_class' => 'f-menu' )); } ?></div>
<div id="creditline"><?php echo of_get_option('copyright', '&copy; ' . date("Y"). ': ' . get_bloginfo( 'name' )); ?> <span class="credit">| Easy Theme by: <a href="http://d5creation.com" target="_blank"><img  src="<?php echo get_template_directory_uri(); ?>/images/d5logofooter.png" /> D5 Creation</a> | Powered by: <a href="http://wordpress.org" target="_blank">WordPress</a></span></div>
<?php wp_footer(); ?>
</body>
</html>