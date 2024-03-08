# Contributors Guidelines

👋 Thank you for considering contributing to our project! We appreciate your interest in improving the communication with the USOS API.

### Project Objective

The goal of this project is to develop a library that facilitates easy communication with the USOS API. While the API documentation may seem sufficient at first glance, it often lacks detailed information on returned types, making working with the API frustrating. Our aim is to do it right the first time and provide backend developers with a straightforward way to interact with USOS.

### Contribution Guidelines

Before contributing, please familiarize yourself with our contribution principles within WMS:

- If you're unable to test a module and no other developers can do so either, do your best to ensure it works. For instance, you can use the `Object` class instead of a specific object if you're unsure about what the endpoint will return. Be sure to annotate methods/modules with `@NotTested` and mention the reason in the annotation body.

- When adding a method, ensure that clients can practically use ALL possible arguments. If some arguments are optional, we should expose all their permutations ideally. However, unnecessary/obvious arguments can be omitted. You can consider using already established patterns within the code.

- If an argument is a string that only takes a few constant values (e.g., the `lang` argument in many modules only accepts "en" or "pl"), use an Enum containing all possible values.

### Coding Standards

- This is library code, so long classes are allowed for simplicity.

- We prefer method invocation on the API to resemble HTTP calls, e.g., `userApi.users().user()` corresponds to a call to `/services/users/user`.

- Method types should correspond to the actual type, e.g., `data` should be `LocalDate`, not `String`. Conversion should occur within the API module.

### Authentication and Security

- If a module API requires at least one user token for a request, the API class should extend `UsosUserApiDefinition`. Otherwise, it should extend `UsosServerApiDefinition`.

- If a class extends `UsosUserApiDefinition` but has methods that do not require OAuth/access tokens (i.e., any method uses `request` instead of `requestWithAccessToken`), these methods should be added to the server API and exposed through a dedicated interface (see the TimeTable module).

- We expose two equally important classes within the `Usos` class - `UsosUserApi` and `UsosServerApi`. These classes compose all API classes that can be called with access tokens and consumer tokens, respectively.

- Never expose objects from the library code that are not ours (anti-corruption layer, see `Usos.class` and OAuth).

### Testing and Logging

- Use the debug module for testing your implementation. It already includes a dependency on `usos4j` in your `pom.xml`, allowing you to use the library like a real client.

- Avoid logging directly to the console (`System.out.println`). Instead, use a logging framework like Slf4j logback.

### Development Environment

- Preferably, write integration tests for any new methods. Not only do they increase coverage, but they also handle logging automatically. Check the existing API tests for guidance. Details on this solution can be found in `HeadlessUsosProviderExtension`.

### Communication

If you have any questions, feel free to reach out to `@gniadeck`, `@MDybek` or any other contributor.

### Module Integration

The library doesn't provide access to all modules for now. We are very open for PRs containing integrations of more modules




Thank you for your cooperation! Your contributions will help us enhance our project and make working with the USOS API smoother for everyone. 🚀