package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches =  (0.0029484821524663665 + 0.0029229016217046495 + 0.002942851128369217 + 0.0029449372915088426 + 0.0029242569639630037)/5;
        ThreeWheelConstants.strafeTicksToInches = (0.002881137363098747 + 0.002881137363098747 + 0.0028859990752756358 + 0.002869235608335478 + 0.0028457820164093065)/5;
        ThreeWheelConstants.turnTicksToInches = .00067156 ;  //.001989436789
        ThreeWheelConstants.leftY = 1;
        ThreeWheelConstants.rightY = -1;
        ThreeWheelConstants.strafeX = -2.5;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "rightFront";
        ThreeWheelConstants.leftEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.rightEncoderDirection = Encoder.REVERSE;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
    }
}




