package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaNegatiivinenLuku() {
        double expectedSaldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-2);

        // saldon ei pitäisi olla muuttunut
        assertEquals(expectedSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaEnemmanKuinMahtuu() {
        double paljonkoMahtuu = varasto.paljonkoMahtuu();
        varasto.lisaaVarastoon(paljonkoMahtuu + 2);

        // saldon pitäisi olla sama kuin tilavuus
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenVarastostaNegatiivinen() {
        varasto.lisaaVarastoon(8);
        double expectedResult = varasto.getSaldo();

        varasto.otaVarastosta(-4);

        // varastoston määrän pitäisi olla sama kuin ennen ottamista
        assertEquals(expectedResult, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenVarastostaEnemmanKuinOn() {
        //double tyhja = 0;
        double tyhja = 1;
        varasto.lisaaVarastoon(8);
        double varastossaNyt = varasto.getSaldo();

        varasto.otaVarastosta(varastossaNyt + 6);

        // varastoston määrän pitäisi olla tyhja
        assertEquals(tyhja, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
}