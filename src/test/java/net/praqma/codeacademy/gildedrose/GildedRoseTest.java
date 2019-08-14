package net.praqma.codeacademy.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    public void old_brie() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("brie is a higher quality",app.items[0].quality>1 );
        
    }
    @Test
    public void never_over_50() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue("brie is higher quality than it should be able to",app.items[0].quality==50 );
        
    }

    @Test
    public void sulfuras_quality_static() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras should be unchangeed", items[0].quality, 80);
        assertEquals("Sulfuras (-1) should be unchangeed", items[1].quality, 80);
    }

    @Test
    public void post_selldate_degration() {
        Item[] items = new Item[] {
            new Item("test", -1, 2)
        };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertTrue("Item should degrade twice as fast on passing expiration date",app.items[0].quality==0 );
    }

    @Test
    public void item_quality_is_never_negative() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 0), //
            new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 0), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 0), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 0) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();

            for(Item item : items){
                assertTrue("Item qualitaly never drops below 0",app.items[0].quality>=0 );
            }
    }

}
