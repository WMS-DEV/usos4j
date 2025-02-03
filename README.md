# usos4j - Java client for USOS API

✨ using USOS API just became fun ✨

## Features

- **Simplified Communication**: Easily interact with the USOS API without dealing with the complexities of low-level HTTP requests.
- **Detailed Documentation**: Comprehensive documentation, with practical demos, to guide developers on how to use the library effectively.
- **Authentication Handling**: Streamlined handling of OAuth and access tokens for secure communication with the USOS API.
- **Modular Design**: Organized into modules for easy integration and maintenance.
- **Well tested**: Includes integration and architecture tests to ensure the reliability and stability of the library.
- **Open for extension**: The library includes constructs, that make it easy to integrate additional modules, without relying on the library development itself, see generic endpoint demo below

## Getting Started

### Note: JDK 17+ is required

The library is available on maven-central, use this code snippet to use it!
```XML
<dependency>
    <groupId>pl.wmsdev</groupId>
    <artifactId>usos4j</artifactId>
    <version>1.1.4</version>
</dependency>
```

Consider checking out our demos on how to use usos4j on basic scenarios:

- [Console application with code-based authentication](https://github.com/WMS-DEV/usos4j/tree/master/demo)
- [Web application with oAuth authentication](https://github.com/WMS-DEV/usos4j/tree/master/spring-demo)
- [Full Spring Security integration - log in to your aplication through USOS](https://github.com/WMS-DEV/usos4j/tree/master/spring-security-demo)

We also offer you an access to a generic endpoint, which can make it easier for you to access USOS as you wish, providing the authentication support, here is an example on how you can use it:
```java
var usos = Usos.builder()......build();
var serverApi = usos.getServerApi();
var usosApiRefModuleResponse = serverApi.generic()
                .request("services/apiref/module", Map.of("name", List.of("services/crstests")), UsosApiRefModule.class); // this is performed as an unauthenticated API client
var userApi = usos.getUserApi();
var userApiRefModuleResponse = userApi.generic()
                .request("services/apiref/module", Map.of("name", List.of("services/crstests")), UsosApiRefModule.class); // this is performed as a user
```

## Integrated modules:
The library supports
- apiref
- apisrv
- calendar
- cards
- courses
- credits
- fac
- feedback
- geo
- grades
- groups
- housing
- mailing
- news
- payments
- phones
- photos
- plctests
- progs
- registrations
- terms
- theses
- timetable
- rest of the modules thanks to generic endpoint implementation

We decided to integrate the most interesting endpoints from the user perspective. We are very open for contributions integrating more modules, but it's not so easy, as we are not in possesion of any USOS testing enviroment, meaning we can only integrate modules, that the universities of contributors use.

## Documentation

- Checkout our contribution guidelines
- To fully understand the concepts in the library, consider visiting debug module, where you will be able to find integration tests

## System configuration

You can find available websites for generating USOSAPI keys in [the installations list](https://apps.usos.edu.pl/developers/api/definitions/installations/) 

Usos integration tests and demos, are based on this system enviroments configuration:

- usos_baseurl - base url of USOSAPI that you would like to use. For Politechnika Wrocławska it would be https://apps.usos.pwr.edu.pl/
- usos_consumerKey - Consumer key. You can generate it on a proper USOS APPS website. For Politechnika Wrocławska it would be https://apps.usos.pwr.edu.pl/developers/
- usos_consumerSecret - Consumer secret, also generated from the website above
- usos_login - login to your USOS account, if you wish to use HeadlessUsosProvider (automatic possesion of authorization tokens)
- usos_password - password to your USOS account, as above

## Contributing

We welcome contributions from the community! If you would like to contribute to the USOS API Communication Library, please refer to the [Contributors Guidelines](git/usos4j-prod/CONTRIBUTORS.md) for instructions on how to get started.

## Support

If you encounter any issues or have any questions about usos4j, please feel free to [open an issue](../../issues) on GitHub. We are here to help!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

We would like to thank all the contributors who have helped make this project possible. Especially the WMS creators of the library:
- [@gniadeck](https://github.com/gniadeck)
- [@MDybek](https://github.com/MDybek)
- [@Foxyg3n](https://github.com/Foxyg3n)
- [@pszumanski](https://github.com/pszumanski)
- [@michalK00](https://github.com/michalK00)

---

Thank you for choosing usos4j. We hope it simplifies your interaction with the USOS API and enhances your development experience! 🚀
Feel free to share any amazing project you create with it B)
