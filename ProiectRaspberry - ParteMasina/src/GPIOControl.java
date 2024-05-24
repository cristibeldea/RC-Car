import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GPIOControl {
    private final static Logger log = LoggerFactory.getLogger(GPIOControl.class);

    DigitalOutput pin17, pin27, pin22, pin23;

    //17 27 22 23
    void setupPins() throws Exception {
        var pi4j = Pi4J.newAutoContext();

        DigitalOutputConfigBuilder config1 = DigitalOutput.newConfigBuilder(pi4j)
                .id("output1")
                .name("Digital Output 1")
                .address(17)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);

        DigitalOutputConfigBuilder config2 = DigitalOutput.newConfigBuilder(pi4j)
                .id("output2")
                .name("Digital Output 2")
                .address(27)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);

        DigitalOutputConfigBuilder config3 = DigitalOutput.newConfigBuilder(pi4j)
                .id("output3")
                .name("Digital Output 3")
                .address(22)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);

        DigitalOutputConfigBuilder config4 = DigitalOutput.newConfigBuilder(pi4j)
                .id("output4")
                .name("Digital Output 4")
                .address(23)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW);

        pin17 = pi4j.create(config1);
        pin27 = pi4j.create(config2);
        pin22 = pi4j.create(config3);
        pin23 = pi4j.create(config4);

    }

    int input;

    public void setInput(int input) {
        this.input = input;
    }

    public void managePinOutput() {
        if (input == 1) {

            log.info("fata");
            pin17.high();
            pin27.low();
            pin22.low();
            pin23.low();
        }

        if (input == 2) {
            log.info("spate");
            pin17.low();
            pin27.low();
            pin22.high();
            pin23.low();

        }

        if (input == 3) {
            log.info("fata-stanga");
            pin17.high();
            pin27.high();
            pin22.low();
            pin23.low();

        }

        if (input == 4) {
            log.info("fata-dreapta");
            pin17.high();
            pin27.low();
            pin22.low();
            pin23.high();
        }

        if (input == 5) {
            log.info("spate-stanga");
            pin17.low();
            pin27.high();
            pin22.high();
            pin23.low();
        }

        if (input == 6) {
            log.info("spate-dreapta");
            pin17.low();
            pin27.low();
            pin22.high();
            pin23.high();
        }

        if (input == 0) {
            log.info("stai pe loc");
            pin17.low();
            pin27.low();
            pin22.low();
            pin23.low();
        }
    }

}
