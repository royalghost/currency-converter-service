# Currency Converter Service
A Simple Service demonstrating the use of Netflix Eureka for providing service for currency converter

# Service Provider
	@EnableEurekaServer
	@SpringBootApplication
	@RestController
	public class CurrencyConverterApp {

	private static final Logger log = LoggerFactory.getLogger(CurrencyConverterApp.class);
	
	@Value("${currency_converter_url}")
	private String CURRENCY_CONVERTER_URL;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApp.class, args);
	}
	
	@RequestMapping(value="convert/{currencyFrom}/{currencyTo}", produces=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public CurrencyConversion convertCurrency(@PathVariable("currencyFrom") String currencyFrom, 
									@PathVariable("currencyTo") String currencyTo) {
		log.info("Reading conversion from url " + this.CURRENCY_CONVERTER_URL);
		CurrencyConversion currencyConversion = new RestTemplateBuilder().build().getForObject(CURRENCY_CONVERTER_URL.replace("CURRENCY_FROM", currencyFrom).replace("CURRENCY_TO", currencyTo), CurrencyConversion.class);
		return currencyConversion;
	}
	}
	
# application.yml
	api_layer_accesskey: 
	currency_converter_url: "http://apilayer.net/api/live?access_key=${api_layer_accesskey}&currencies=CURRENCY_TO&source=CURRENCY_FROM&format=1"
	server:
		port: 8761
	eureka:
		client:
			registerWithEureka: false
			fetchRegistry: false

# Configuration
1. Create your api access key from http://apilayer.net and copy it for api_layer_accesskey

# Payload
## Request
Converting USD to CAD
http://localhost:8761/convert/USD/CAD

## Response
{"source":"USD","timestamp":1524454683,"quotes":{"USDCAD":1.27564}}
