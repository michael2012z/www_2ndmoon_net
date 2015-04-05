<?php
/* Easy Theme's Right Sidebar Area
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/
?>
<div id="right-sidebar">

<div id="social">

<?php if (of_get_option('gplus-link', '#') !='') : ?>
<a href="<?php echo of_get_option('gplus-link', '#'); ?>" class="gplus-link" target="_blank"></a>
<?php  endif; if (of_get_option('li-link', '#') !='') : ?>
<a href="<?php echo of_get_option('li-link', '#'); ?>" class="li-link" target="_blank"></a>
<?php  endif; if (of_get_option('feed-link', '#') !='') : ?>
<a href="<?php echo of_get_option('feed-link', '#'); ?>" class="feed-link" target="_blank"></a>
<?php  endif; ?>
</div>	
<?php get_search_form(); ?>	

<?php if ( ! dynamic_sidebar( 'sidebar-1' ) ) : ?>

				<aside id="archives" class="widget">
					<h3 class="widget-title">Archives</h3>
					<ul>
						<?php wp_get_archives( array( 'type' => 'monthly' ) ); ?>
					</ul>
				</aside>

				<aside id="meta" class="widget">
					<h3 class="widget-title">Meta</h3>
					<ul>
						<?php wp_register(); ?>
						<li><?php wp_loginout(); ?></li>
						<?php wp_meta(); ?>
					</ul>
				</aside>

<?php endif; // end sidebar widget area ?>
</div>