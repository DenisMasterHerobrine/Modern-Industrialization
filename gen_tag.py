import sys
import json

tags = [
    "bronze_ingot",
    "bronze_nugget",
    "copper_ingot",
    "copper_nugget",
    "copper_ore",
    "tin_ingot",
    "tin_nugget",
    "tin_ore"
]

if __name__ == '__main__':
    for tag in tags:
        with open("src/main/resources/data/c/tags/items/" + tag + "s.json", "w") as f:
            jsonf = {
                "replace": False,
                "values": [
                    "modern_industrialization:" + tag
                ]
            }
            json.dump(jsonf, f, indent=4)

    java_class ="""
package aztech.modern_industrialization;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

/**
 * This is auto-generated, don't edit by hand!
 */
public class MITags {\n"""
    for tag in tags:
        java_class += '    private static final Tag<Item> %sS = TagRegistry.item(new Identifier("c", "%ss"));\n' % (tag.upper(), tag)
    java_class += """
    public static void setup() {
        // Will register the tags by loading the static fields!
    }
}
"""
    with open("src/main/java/aztech/modern_industrialization/MITags.java", "w") as f:
        f.write(java_class)