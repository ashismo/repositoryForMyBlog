package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGlHeader is a Querydsl query type for GlHeader
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGlHeader extends EntityPathBase<GlHeader> {

    private static final long serialVersionUID = -1144844258L;

    public static final QGlHeader glHeader = new QGlHeader("glHeader");

    public final NumberPath<Integer> glHeaderCode = createNumber("glHeaderCode", Integer.class);

    public final StringPath glHeaderDesc = createString("glHeaderDesc");

    public final ListPath<GlSubHeader, QGlSubHeader> glSubHeaders = this.<GlSubHeader, QGlSubHeader>createList("glSubHeaders", GlSubHeader.class, QGlSubHeader.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public QGlHeader(String variable) {
        super(GlHeader.class, forVariable(variable));
    }

    public QGlHeader(Path<? extends GlHeader> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGlHeader(PathMetadata<?> metadata) {
        super(GlHeader.class, metadata);
    }

}

