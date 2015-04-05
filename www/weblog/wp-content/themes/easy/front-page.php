<?php
/*
	Easy Theme's Front Page to Display the Home Page if Selected
	Copyright: 2013, D5 Creation, www.d5creation.com
	Based on the Simplest D5 Framework for WordPress
	Since Easy 1.0
*/
?>

<?php get_header(); ?>
			<div id="container"><div id="content">
            <div id="slideshow"><ul class="bjqs">
			
            <?php $easy_args = easy_ppp(); query_posts( $easy_args ); if (have_posts()) : while (have_posts()) : the_post();?>
            
          <li><a href="<?php the_permalink(); ?>"><?php the_post_thumbnail('slide-thumb'); ?><div class="post-slide"><h2><?php the_title(); ?></h2><?php  $EasyExcerptLength=15; the_excerpt(); ?></div></a></li>
			
			<?php endwhile; endif; wp_reset_query(); ?>
      
            </ul>
			</div><div class="clear"> </div>  
       
 
<h1 id="heading"><?php echo of_get_option('heading_text', 'WordPress is web software you can use to create a <a href="#">beautiful website or blog</a>. We like to say that <a href="http://wordpress.org/">WordPress</a> is both free and priceless at the same time.'); ?></h1>

<?php if (have_posts()) : while (have_posts()) : the_post();?>
<div <?php post_class(); ?> id="post-<?php the_ID(); ?>">
 <h2 class="post-title"><a href="<?php the_permalink(); ?>"><?php the_title();?></a></h2>
 <div class="content-ver-sep"> </div>
 <div class="entrytext">
 <?php the_post_thumbnail('thumbnail'); easy_content(); ?>
 <div class="clear"> </div>
 <div class="up-bottom-border"> </div>
 </div></div>
 
 <?php endwhile; ?>

<div id="page-nav">
<div class="alignleft"><?php previous_posts_link('Previous Entries' ) ?></div>
<div class="alignright"><?php next_posts_link('Next Entries') ?></div>
</div>
   
<?php endif; ?>




<?php if ( of_get_option('bottom-quotation', 'All the developers of D5 Creation have come from the disadvantaged part or group of the society. All have established themselves after a long and hard struggle in their life ----- D5 Creation Team') != '' ) : ?>
<div class="content-ver-sep"> </div>
<div class="fpage-quote">
<div class="customers-comment">
<ul>
<li><q><?php echo of_get_option('bottom-quotation', 'All the developers of D5 Creation have come from the disadvantaged part or group of the society. All have established themselves after a long and hard struggle in their life ----- D5 Creation Team'); ?></q></li>
</ul></div></div>
<?php endif; ?>

</div>

<?php get_sidebar(); ?>
<?php get_footer(); ?>