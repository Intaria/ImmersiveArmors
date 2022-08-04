package immersive_armors.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record FlowingText(List<OrderedText> lines, float scale) {

    public interface Factory {
        /**
         * Scales the given text to fit a desired width and height.
         */
        static FlowingText wrapLines(TextRenderer renderer, Text text, int maxBlockWidth, int maxBlockHeight) {
            float scale = 1;

            List<OrderedText> output;

            do {
                output = renderer.wrapLines(text, (int)Math.ceil(maxBlockWidth / scale));

                if (output.size() * 10 * scale <= maxBlockHeight) {
                    break;
                }

                scale -= 0.01F;
            } while (scale > 0.08F);

            // We trim excess lines in the event fitting isn't perfect
            int maxLines = (int)Math.ceil(maxBlockHeight / (10 * scale));

            return new FlowingText(output.stream().limit(maxLines).collect(Collectors.toList()), scale);
        }
    }

    public static List<Text> wrap(Text text, int maxWidth) {
        return MinecraftClient.getInstance().textRenderer.getTextHandler().wrapLines(text, maxWidth, Style.EMPTY).stream().map(line -> {
            MutableText compiled = Text.literal("");
            line.visit((s, t) -> {
                compiled.append(Text.literal(t).setStyle(s));
                return Optional.empty();
            }, text.getStyle());
            return compiled;
        }).collect(Collectors.toList());
    }
}
