# osgifx
Example of application running JavaFX 2 in an OSGi felix container.

view - main JavaFX view module, contains one view to manage Persosn.
core - define the model, persistence.xml and service interfaces that are used by the views.
core-impl - implements the service interfaces using the entityManagerFactory provided by OSGi.
felix.launcher - puts all dependency bundles in one apache felix instance and run it.
