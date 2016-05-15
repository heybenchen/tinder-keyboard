package stupidhackathon.tinderkeyboard;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import java.util.Random;

public class LetterProfile {
    private Character character;
    private String typeface;
    private Color color;
    private Drawable background;

    private Random random = new Random();

    private String[] typefaces = new String[] {
            "Admiration Pains.ttf",
            "AlexBrush-Regular.ttf",
            "Angeline Vintage_Demo.otf",
            "Angeline Vintage_Demo.ttf",
            "Art Brewery.ttf",
            "Banten Unfamous 2.ttf",
            "Bichette.ttf",
            "Brainstorm Bold Italic.otf",
            "Brainstorm Bold.otf",
            "Brainstorm Italic.otf",
            "Brainstorm.otf",
            "Bulldozer.ttf",
            "CFGreenCorn-Regular.ttf",
            "Chunkfive.otf",
            "Cronus Italic.otf",
            "Cronus Round.otf",
            "Deslucida-black-font-FFP.ttf",
            "End Of Anthropocene.otf",
            "Exo-Bold.otf",
            "Exo-BoldItalic.otf",
            "Exo-Italic.otf",
            "Exo-Medium.otf",
            "Exo-MediumItalic.otf",
            "Exo-Regular.otf",
            "Exo-SemiBold.otf",
            "Exo-Thin.otf",
            "Exo-ThinItalic.otf",
            "Exostencil Italic.otf",
            "Exostencil.otf",
            "From Cartoon Blocks.ttf",
            "KaushanScript-Regular.otf",
            "marguerite.ttf",
            "mawi.otf",
            "MotionPicture_PersonalUseOnly.ttf",
            "MRF Lemonberry Sans.otf",
            "NMFDisplay-Regular.ttf",
            "Panama-Road-Rg-FFP.otf",
            "Panama-Road-Rg-FFP.ttf",
            "PistolGripPump.ttf",
            "Pretty Girls Script Demo.ttf",
            "Qube.otf",
            "Qube.ttf",
            "riesling.ttf",
            "SinkinSans-100ThinItalic.otf",
            "SinkinSans-200XLight.otf",
            "SinkinSans-200XLightItalic.otf",
            "SinkinSans-400Regular.otf",
            "SinkinSans-800BlackItalic.otf",
            "Strawberry Muffins Demo.ttf",
            "Sunbreath.otf",
            "Sunbreath.ttf",
            "Texas Woman Demo.ttf",
            "The Heart Maze Demo.ttf",
            "waltograph42.otf",
            "waltographUI.ttf",
            "Yoko Smile Regular.otf"
    };

    public LetterProfile(Character character) {
        this.character = character;
        this.typeface = typefaces[random.nextInt(typefaces.length)];
    }
}
