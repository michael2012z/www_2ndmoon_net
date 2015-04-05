#ifndef _MEM_MAP_H_
#define _MEM_MAP_H_

#define MAP_DESC_TYPE_SECTION	0
#define MAP_DESC_TYPE_PAGE	1

struct map_desc {
	unsigned long physical;
	unsigned long virtual;
	unsigned long length;
	unsigned int type;
};

#define MAP_ITEM_SHIFT		20 /* cover 20 bits */ 
#define map_length_to_count(x)		((x) >> MAP_ITEM_SHIFT)
#define map_count_to_length(x)		((x) << MAP_ITEM_SHIFT)

void init_pages_map();

#endif
