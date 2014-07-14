/*
 * Copyright (C) 2010, T-Mobile USA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.pm;

import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.content.res.Resources;

/**
 * Overall information about "theme" package.  This corresponds
 * to the information collected from AndroidManifest.xml (theme tag).
 *
 * Below is an example of theme tag
 *    <theme
 *        pluto:name="Pluto Default"
 *        pluto:preview="@drawable/preview"
 *        pluto:author="John Doe"
 *        pluto:ringtoneFileName="media/audio/ringtone.mp3"
 *        pluto:notificationRingtoneFileName="media/audio/locked/notification.mp3"
 *        pluto:copyright="T-Mobile, 2009"
 *    />
 *
 * @hide
 */
public final class ThemeInfo extends BaseThemeInfo {
    private enum AttributeIndex {
        THEME_PACKAGE_INDEX,
        PREVIEW_INDEX,
        AUTHOR_INDEX,
        THEME_INDEX,
        THEME_STYLE_NAME_INDEX,
        THUMBNAIL_INDEX,
        RINGTONE_FILE_NAME_INDEX,
        NOTIFICATION_RINGTONE_FILE_NAME_INDEX,
        WALLPAPER_IMAGE_INDEX,
        COPYRIGHT_INDEX,
        RINGTONE_NAME_INDEX,
        NOTIFICATION_RINGTONE_NAME_INDEX,
        STYLE_INDEX;

        public static AttributeIndex get(int ordinal) {
            return values()[ordinal];
        }
    };

public final class ThemeInfo implements Parcelable {

    /**
     * The name of the wallpaper image file.
     * Specifies a relative path in assets subfolder.
     * If the parent's name is "locked" - DRM protected.
     *
     * @see wallpaperImage attribute
     */
    public String wallpaperImageName;

    /**
     * The name of the favorites background image file.
     * Specifies a relative path in assets subfolder.
     * If the parent's name is "locked" - DRM protected.
     *
     * @see favesBackground attribute
     *
     */
    public String favesImageName;

    /**
     * The name of the favorite apps background image file.
     * Specifies a relative path in assets subfolder.
     * If the parent's name is "locked" - DRM protected.
     *
     * @see favesAppBackground attribute
     *
     */
    public String favesAppImageName;

    /**
     * The resource id of theme thumbnail.
     * Specifies a theme thumbnail image resource as @drawable/foo.
     *
     * @see themeThumbprint attribute
     *
     */
    public int thumbnail = - 1;

    /**
     * The resource id of Android UI Style.
     * Specifies an Android UI Style as @style/bar.
     *
     * @see androidUiStyle attribute
     * 
     */
    public int theme = -1;

    /**
     * The name of the theme (as displayed by UI).
     *
     * @see themeName attribute
     *
     */
    public String themeName;

    /**
     * The name of the call ringtone audio file.
     * Specifies a relative path in assets subfolder.
     * If the parent's name is "locked" - DRM protected.
     *
     * @see callRingtone attribute
     *
     */
    public String ringtoneName;

    /**
     * The name of the notification ringtone audio file.
     * Specifies a relative path in assets subfolder.
     * If the parent's name is "locked" - DRM protected.
     *
     * @see notificationRingtone attribute
     *
     */
    public String notificationRingtoneName;

    /**
     * The author name of the theme package.
     *
     * @see themeAuthor attribute
     *
     */
    public String author;

    /**
     * The copyright text.
     *
     * @see themeCopyright attribute
     *
     */
    public String copyright;

    // There is no corresposponding flag in manifest file
    // This flag is set to true iff any media resource is DRM protected
    public boolean isDrmProtected = false;

    /**
     * {@link #themePackage}
     *
     */
    private static final int THEME_PACKAGE_INDEX = 0;

    /**
     * {@link #thumbnail}
     *
     */
    private static final int THUMBNAIL_INDEX = 1;

    /**
     * {@link #author}
     *
     */
    private static final int AUTHOR_INDEX = 2;

    /**
     * {@link #ringtoneName}
     *
     */
    private static final int RINGTONE_NAME_INDEX = 3;

    /**
     * {@link #notificationRingtoneName}
     *
     */
    private static final int NOTIFICATION_RINGTONE_NAME_INDEX = 4;

    /**
     * {@link #favesImageName}
     *
     */
    private static final int FAVES_IMAGE_NAME_INDEX = 5;

    /**
     * {@link #favesAppImageName}
     *
     */
    private static final int FAVES_APP_IMAGE_NAME_INDEX = 6;

	/**
     * {@link #wallpaperImageName}
     *
     */
    private static final int WALLPAPER_IMAGE_NAME_INDEX = 7;

    /**
     * {@link #copyright}
     *
     */
    private static final int COPYRIGHT_INDEX = 8;

    /**
     * {@link #theme}
     *
     */
    private static final int THEME_INDEX = 9;

     /**
     * {@link #ringtoneName}
     *
     */
    private static final int RINGTONE_NAME_INDEX = 10;

    /**
     * {@link #notificationRingtoneName}
     *
     */
    private static final int NOTIFICATION_RINGTONE_NAME_INDEX = 11;

    /**
     * {@link #soundPackName}
     *
     */
    private static final int SOUNDPACK_NAME_INDEX = 12;

    /**
     * {@link #parentThemeId}
     *
     */
    private static final int PARENT_THEME_INDEX = 13;

    /**
     * {@link #parentThemePackageName}
     *
     */
    private static final int PARENT_THEME_PACKAGE_INDEX = 14;

    /**
     * {@link #parentThemePackageName}
     *
     */
    private static final int THEME_HAS_COLOR_PALETTE_INDEX = 15;


    private static final String [] compulsoryAttributes = new String [] {
        "name",
        "preview",
        "author",
        "themeId",
        "styleName",
    };

    private static final String [] optionalAttributes = new String [] {
        "thumbnail",
        "ringtoneFileName",
        "notificationRingtoneFileName",
        "wallpaperImage",
        "copyright",
        "ringtoneName",
        "notificationRingtoneName",
        "styleId",
        "soundpackName",
        "parentThemeId",
        "parentThemePackageName",
        "hasColorPalette",
    };

    private static final Map<String, AttributeIndex> sAttributesLookupTable;

    static {
        sAttributesLookupTable = new HashMap<String, AttributeIndex>();
        for (int i = 0; i < compulsoryAttributes.length; i++) {
            sAttributesLookupTable.put(compulsoryAttributes[i], AttributeIndex.get(i));
        }

        for (int i = 0; i < optionalAttributes.length; i++) {
            sAttributesLookupTable.put(optionalAttributes[i],
                    AttributeIndex.get(compulsoryAttributes.length + i));
        }
    }

    public ThemeInfo(XmlPullParser parser, Resources res, AttributeSet attrs) throws XmlPullParserException {
        super();

        Map<String, AttributeIndex> tempMap =
                new HashMap<String, AttributeIndex>(sAttributesLookupTable);
        int numberOfCompulsoryAttributes = 0;
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            if (!ApplicationInfo.isPlutoNamespace(parser.getAttributeNamespace(i))) {
                continue;
            }
            String key = attrs.getAttributeName(i);
            if (tempMap.containsKey(key)) {
                AttributeIndex index = tempMap.get(key);
                tempMap.remove(key);

                if (index.ordinal() < compulsoryAttributes.length) {
                    numberOfCompulsoryAttributes++;
                }
                switch (index) {
                    case THEME_PACKAGE_INDEX:
                        // theme name
                        name = getResolvedString(res, attrs, i);
                        break;

                    case THUMBNAIL_INDEX:
                        // theme thumbprint
                        thumbnailResourceId = attrs.getAttributeResourceValue(i, 0);
                        break;

                    case AUTHOR_INDEX:
                        // theme author
                        author = getResolvedString(res, attrs, i);
                        break;

                    case THEME_INDEX:
                        // androidUiStyle attribute
                        themeId = attrs.getAttributeValue(i);
                        break;

                    case THEME_STYLE_NAME_INDEX:
                        themeStyleName = getResolvedString(res, attrs, i);
                        break;

                    case RINGTONE_FILE_NAME_INDEX:
                        // ringtone
                        ringtoneFileName = attrs.getAttributeValue(i);
                        changeDrmFlagIfNeeded(ringtoneFileName);
                        break;

                    case NOTIFICATION_RINGTONE_FILE_NAME_INDEX:
                        // notification ringtone
                        notificationRingtoneFileName = attrs.getAttributeValue(i);
                        changeDrmFlagIfNeeded(notificationRingtoneFileName);
                        break;

                    case WALLPAPER_IMAGE_INDEX:
                        // wallpaperImage attribute
                        wallpaperResourceId = attrs.getAttributeResourceValue(i, 0);
                        break;

                    case COPYRIGHT_INDEX:
                        // themeCopyright attribute
                        copyright = getResolvedString(res, attrs, i);
                        break;

                    case RINGTONE_NAME_INDEX:
                        // ringtone UI name
                        ringtoneName = attrs.getAttributeValue(i);
                        break;

                    case NOTIFICATION_RINGTONE_NAME_INDEX:
                        // notification ringtone UI name
                        notificationRingtoneName = attrs.getAttributeValue(i);
                        break;

                    case STYLE_INDEX:
                        styleResourceId = attrs.getAttributeResourceValue(i, 0);
                        break;

                    case PREVIEW_INDEX:
                        // theme thumbprint
                        previewResourceId = attrs.getAttributeResourceValue(i, 0);
                        break;

                    case PARENT_THEME_INDEX:
                        parentThemeId = attrs.getAttributeIntValue(i, -1);
                        break;  

                    case PARENT_THEME_PACKAGE_INDEX:
                        parentThemePackageName = attrs.getAttributeValue(i);
                        break;  

                    case THEME_HAS_COLOR_PALETTE_INDEX:
                        hasColorPalette = attrs.getAttributeValue(i).equalsIgnoreCase("true");
                        break;
                }
            }
        }
        if (numberOfCompulsoryAttributes < compulsoryAttributes.length) {
            throw new XmlPullParserException("Not all compulsory attributes are specified in <theme>");
        }
    }

    public static final Parcelable.Creator<ThemeInfo> CREATOR
            = new Parcelable.Creator<ThemeInfo>() {
        public ThemeInfo createFromParcel(Parcel source) {
            return new ThemeInfo(source);
        }

        public ThemeInfo[] newArray(int size) {
            return new ThemeInfo[size];
        }
    };

    private ThemeInfo(Parcel source) {
        super(source);
    }
}
