package in.akshat.BadgeView;

//imports
import android.view.Gravity;
import android.view.View;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.common.*;
import in.akshat.BadgeView.library.Badge;
import in.akshat.BadgeView.library.QBadgeView;
import android.app.Activity;
import android.content.Context;

@DesignerComponent(version = 1, // Update version here, You must do for each new release to upgrade your extension
        versionName = "1.0.0",
        description = "Extension By Akshat. Allows you to create A Badge View To And Android Visible Component With My Customization. V1",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://cdn.iconscout.com/icon/free/png-256/badge-964-910295.png") // Change your extension's icon from here; can be a direct url
@SimpleObject(external = true)
public class BadgeView extends AndroidNonvisibleComponent {
    QBadgeView qBadgeView;
    private ComponentContainer container;
    private Context context;
    private Activity activity;
    public Boolean Animate = true;
    public int badgeBGColor;
    public boolean strokeEnabled;
    public int strokeColour;
    public float strokeWidth;
    public float badgePadding;
    public boolean DpValueEnabled;
    public boolean shadowenabled;
    public int badgeTextColour;
    public float badgeTextSize;

    public BadgeView(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        context = (Context) container.$context();
        activity = (Activity) context;
    }

    //MyCode Start

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "true")
    @SimpleProperty(description = "Sets If Dp Value Enabled")
    public void IsDpValue(boolean choice){
        DpValueEnabled = choice;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "true")
    @SimpleProperty(description = "Sets If Animation Enabled")
    public void Animate(boolean choice){
        Animate = choice;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, defaultValue = "true")
    @SimpleProperty(description = "Sets If Badge Shadow Enabled")
    public void ShadowEnabled(boolean choice){
        shadowenabled = choice;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT, defaultValue = "8.0")
    @SimpleProperty(description = "Sets The Badge Padding")
    public void BadgePadding(float padding){
        badgePadding = padding;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = Component.DEFAULT_VALUE_COLOR_DEFAULT)
    @SimpleProperty(description = "Sets The Badge Background Colour")
    public void BadgeBackgroundColor(int color){
        badgeBGColor = color;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR, defaultValue = Component.DEFAULT_VALUE_COLOR_DEFAULT)
    @SimpleProperty(description = "Sets The Badge Text Colour")
    public void BadgeTextColor(int color){
        badgeTextColour = color;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT, defaultValue = "8.0")
    @SimpleProperty(description = "Sets The Badge Padding")
    public void BadgeTextSize(float size){
        badgeTextSize = size;
    }

    @SimpleFunction(description = "Creates The Badge")
    public void Create(AndroidViewComponent in, int badgenumber){
        qBadgeView = (QBadgeView) new QBadgeView(context)
                .bindTarget(in.getView())
                .setBadgeNumber(badgenumber)
                .setBadgeTextColor(badgeTextColour)
                .setBadgeTextSize(badgeTextSize, false)
                .setBadgeBackgroundColor(badgeBGColor)
                .setBadgePadding(badgePadding, DpValueEnabled)
                .setShowShadow(shadowenabled)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        String State;
                        switch (dragState) {
                            case STATE_START:
                                State = "STATE_START";
                                break;
                            case STATE_DRAGGING:
                                State = "STATE_DRAGGING";
                                break;
                            case STATE_DRAGGING_OUT_OF_RANGE:
                                State = "STATE_DRAGGING_OUT_OF_RANGE";
                                break;
                            case STATE_SUCCEED:
                                State = "STATE_SUCCEED";
                                break;
                            case STATE_CANCELED:
                                State = "STATE_CANCELED";
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + dragState);
                        }
                        OnDragStateChanged(State);
                    }
                });


    }

    @SimpleEvent(description = "On Drag State Changed")
    public void OnDragStateChanged(String dragState){
        EventDispatcher.dispatchEvent(this, "OnDragStateChanged", dragState);
    }

    @SimpleFunction(description = "Hides The Badge")
    public void HideBadge(){
        qBadgeView.hide(Animate);
    }

    // MyCode Ends
}