package gl1tch.archyclient.Screen;

import gl1tch.archyclient.ArchyClient;
import gl1tch.archyclient.Util.ModConfigHandler;
import gl1tch.archyclient.Util.ModStuffs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.*;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class ArchyClientOptions extends Screen {
    private static final Component TITLE = Component.literal("ArchyClient Options");
    private static final Component TOGGLEAUTOGG = Component.literal("Toggle AutoGG");
    private static final Component AUTOGG = Component.literal("AutoGG message");
    private static final Component TOGGLEAUTOTPAACCEPT = Component.literal("Toggle AutoTPAAccept");
    private static final Component AUTOTPAACCEPT = Component.literal("AutoTPAAccep players (CSV style)t");
    private static final Component TOGGLEAUTOTORTURE = Component.literal("Toggle AutoTorture");
    private static final Component AUTOTORTURE = Component.literal("AutoTorture players (CSV style)");
    private static final Component TOGGLEAUTOSKIPADMIN = Component.literal("Toggle AutoSkipAdmin");
    private static final Component AUTOSKIPADMIN = Component.literal("AutoSkipAdmin minutes");

    private EditBox autoGGBox;
    private EditBox autoTpaAcceptBox;
    private EditBox autoTortureBox;
    private EditBox autoSkipAdminBox;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

    public ArchyClientOptions(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        LinearLayout linearLayout = (LinearLayout)this.layout.addToHeader(LinearLayout.vertical().spacing(8));
        linearLayout.addChild(new StringWidget(TITLE, this.font), LayoutSettings::alignHorizontallyCenter);
        GridLayout gridLayout = new GridLayout();
        gridLayout.defaultCellSetting().paddingHorizontal(4).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(2);
        rowHelper.addChild(this.actionButton(TOGGLEAUTOGG, ArchyClientOptions::toggleAutoGG));
        rowHelper.addChild(this.autoGGBox = this.editBox(AUTOGG, ArchyClient.configOptions.getAutoGG(), ArchyClient.configOptions::setAutoGG));
        rowHelper.addChild(this.actionButton(TOGGLEAUTOTPAACCEPT, ArchyClientOptions::toggleAutoTPAACCEPT));
        rowHelper.addChild(this.autoTpaAcceptBox = this.editBox(AUTOTPAACCEPT, ModStuffs.getListAsString(ArchyClient.configOptions.getAutoTPAACCEPT()), ModStuffs::setAutoTPAACCEPTString));
        rowHelper.addChild(this.actionButton(TOGGLEAUTOTORTURE, ArchyClientOptions::toggleAutoTorture));
        rowHelper.addChild(this.autoTortureBox = this.editBox(AUTOTORTURE, ModStuffs.getListAsString(ArchyClient.configOptions.getAutoTorture()), ModStuffs::setAutoTortureString));
        rowHelper.addChild(this.actionButton(TOGGLEAUTOSKIPADMIN, ArchyClientOptions::toggleAutoSkipAdmin));
        rowHelper.addChild(this.autoSkipAdminBox = this.editBox(AUTOSKIPADMIN, ArchyClient.configOptions.getAutoSkipAdmin(), ArchyClient.configOptions::setAutoSkipAdmin));
        this.layout.addToContents(gridLayout);
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, (buttonx) -> this.onClose()).width(200).build());
        this.layout.visitWidgets((guiEventListener) -> {
            AbstractWidget var10000 = (AbstractWidget)this.addRenderableWidget(guiEventListener);
        });
        this.repositionElements();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public void onClose() {
        boolean shouldSave = false;


        if (ArchyClient.configOptions.getAutoGG() == this.autoGGBox.getValue())
            shouldSave = true;

        for (String str : this.autoTpaAcceptBox.getValue().split(",")) {
            if (!ArchyClient.configOptions.getAutoTPAACCEPT().contains(str))
                shouldSave = true;
        }

        for (String str : this.autoTortureBox.getValue().split(",")) {
            if (!ArchyClient.configOptions.getAutoTorture().contains(str))
                shouldSave = true;
        }

        if (ArchyClient.configOptions.getAutoSkipAdmin() == this.autoSkipAdminBox.getValue())
            shouldSave = true;

        if (shouldSave) {
            ArchyClient.configOptions.setAutoGG(this.autoGGBox.getValue());
            ModStuffs.setAutoTPAACCEPTString(this.autoTpaAcceptBox.getValue());
            ModStuffs.setAutoTortureString(this.autoTortureBox.getValue());
            ArchyClient.configOptions.setAutoSkipAdmin(this.autoSkipAdminBox.getValue());

            ModConfigHandler.writeClientConfig();

            this.rebuildWidgets();
        }

        super.onClose();
    }

    public static void toggleAutoGG() {
        Minecraft minecraft = Minecraft.getInstance();
        String msg = "AutoGG ";


        ArchyClient.configOptions.setAutoGGActive(!ArchyClient.configOptions.getAutoGGActive());
        if (ArchyClient.configOptions.getAutoGGActive()) {
            msg += "Enabled";
        } else {
            msg += "Disabled";
        }

        minecraft.getToastManager().addToast(
                SystemToast.multiline(minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.literal("ArchyClient"), Component.literal(msg))
        );

        ModConfigHandler.writeClientConfig();
    }

    public static void toggleAutoTPAACCEPT() {
        Minecraft minecraft = Minecraft.getInstance();
        String msg = "AutoTPAACCEPT ";


        ArchyClient.configOptions.setAutoTPAACCEPTActive(!ArchyClient.configOptions.getAutoTPAACCEPTActive());
        if (ArchyClient.configOptions.getAutoTPAACCEPTActive()) {
            msg += "Enabled";
        } else {
            msg += "Disabled";
        }

        minecraft.getToastManager().addToast(
                SystemToast.multiline(minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.literal("ArchyClient"), Component.literal(msg))
        );

        ModConfigHandler.writeClientConfig();
    }

    public static void toggleAutoTorture() {
        Minecraft minecraft = Minecraft.getInstance();
        String msg = "AutoTorture ";


        ArchyClient.configOptions.setAutoTortureActive(!ArchyClient.configOptions.getAutoTortureActive());
        if (ArchyClient.configOptions.getAutoTortureActive()) {
            msg += "Enabled";
        } else {
            msg += "Disabled";
        }

        minecraft.getToastManager().addToast(
                SystemToast.multiline(minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.literal("ArchyClient"), Component.literal(msg))
        );

        ModConfigHandler.writeClientConfig();
    }

    public static void toggleAutoSkipAdmin() {
        Minecraft minecraft = Minecraft.getInstance();
        String msg = "AutoSkipAdmin ";


        ArchyClient.configOptions.setAutoSkipAdminActive(!ArchyClient.configOptions.getAutoSkipAdminActive());
        if (ArchyClient.configOptions.getAutoSkipAdminActive()) {
            msg += "Enabled";
        } else {
            msg += "Disabled";
        }

        minecraft.getToastManager().addToast(
                SystemToast.multiline(minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.literal("ArchyClient"), Component.literal(msg))
        );

        ModConfigHandler.writeClientConfig();
    }

    protected void repositionElements() {
        this.layout.arrangeElements();
    }

    protected final EditBox editBox(Component component, String defaultVal, Consumer<String> method) {
        EditBox editBox = new EditBox(this.font, Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, component) {
            public boolean keyPressed(KeyEvent keyEvent) {
                if (keyEvent.key() == 257 || keyEvent.key() == 335) {
                    method.accept(this.getValue());

                    ModConfigHandler.writeClientConfig();

                    minecraft.getToastManager().addToast(
                            SystemToast.multiline(minecraft, SystemToast.SystemToastId.NARRATOR_TOGGLE, Component.literal("ArchyClient"), Component.literal("Set " + component.getString() + " to: " + this.getValue()))
                    );
                }
                return super.keyPressed(keyEvent);
            }
        };


        editBox.setValue(defaultVal);

        return editBox;
    }

    protected final Button actionButton(Component component, Runnable method) {
        return Button.builder(component, (button) -> {
            method.run();
        }).build();
    }
}
