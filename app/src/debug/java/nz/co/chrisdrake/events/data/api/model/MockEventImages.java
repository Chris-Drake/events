package nz.co.chrisdrake.events.data.api.model;

import java.util.Collections;
import nz.co.chrisdrake.events.data.api.model.ImageResource.Image;

public final class MockEventImages {
    static final Image IO = Image.create(true, TransformResource.create(Collections.singletonList(
        Transform.builder()
            .url("https://events.google.com/io2015/images/io15-color.png")
            .width(800)
            .height(600)
            .transformSize(Transform.TransformSize.OTHER)
            .build())));

    static final ImageResource IO_RESOURCE = ImageResource.create(Collections.singletonList(IO));

    private MockEventImages() {
        throw new AssertionError("Non-instantiable.");
    }
}
